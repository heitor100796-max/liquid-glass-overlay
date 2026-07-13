package com.heitor.liquidglass;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Switch toggleSwitch;
    private Button permissionButton;
    private boolean isOverlayActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleSwitch = findViewById(R.id.toggleSwitch);
        permissionButton = findViewById(R.id.permissionButton);

        // Verificar permissão de overlay
        if (!Settings.canDrawOverlays(this)) {
            permissionButton.setVisibility(android.view.View.VISIBLE);
            permissionButton.setOnClickListener(v -> requestOverlayPermission());
        } else {
            permissionButton.setVisibility(android.view.View.GONE);
        }

        // Controlar overlay
        toggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startOverlay();
            } else {
                stopOverlay();
            }
        });
    }

    private void requestOverlayPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    private void startOverlay() {
        if (Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(this, OverlayService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
            isOverlayActive = true;
            Toast.makeText(this, "Liquid Glass Ativado! ✨", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permissão necessária!", Toast.LENGTH_SHORT).show();
            requestOverlayPermission();
        }
    }

    private void stopOverlay() {
        Intent intent = new Intent(this, OverlayService.class);
        stopService(intent);
        isOverlayActive = false;
        Toast.makeText(this, "Liquid Glass Desativado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isOverlayActive) {
            stopOverlay();
        }
    }
}
