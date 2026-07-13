package com.heitor.liquidglass;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;

public class OverlayService extends Service {
    private WindowManager windowManager;
    private FrameLayout overlayView;
    private WindowManager.LayoutParams layoutParams;
    private GestureDetector gestureDetector;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (overlayView == null) {
            createOverlay();
        }
        return START_STICKY;
    }

    private void createOverlay() {
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        overlayView = new FrameLayout(this) {
            @Override
            protected void onDraw(android.graphics.Canvas canvas) {
                super.onDraw(canvas);
                drawLiquidGlassEffect(canvas);
            }
        };

        overlayView.setBackgroundColor(0x00000000); // Transparente
        overlayView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false; // Deixa passar os toques
            }
        });

        // Configurar LayoutParams para o overlay
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | 
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    PixelFormat.TRANSLUCENT
            );
        } else {
            layoutParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | 
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    PixelFormat.TRANSLUCENT
            );
        }

        windowManager.addView(overlayView, layoutParams);
    }

    private void drawLiquidGlassEffect(android.graphics.Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Criar um Paint com efeito glass
        android.graphics.Paint paint = new android.graphics.Paint();
        paint.setAntiAlias(true);

        // Efeito de blur/vidro com gradiente
        android.graphics.Paint glassPaint = new android.graphics.Paint();
        glassPaint.setColor(0x15FFFFFF); // Branco semi-transparente
        glassPaint.setStyle(android.graphics.Paint.Style.FILL);

        // Desenhar padrão ondulado
        for (int i = 0; i < height; i += 100) {
            float offset = (float) Math.sin(System.currentTimeMillis() / 1000.0 + i / 100.0) * 20;
            canvas.drawLine(0, i, width, i + offset, glassPaint);
        }

        // Adicionar brilho no topo
        android.graphics.Paint shinePaint = new android.graphics.Paint();
        shinePaint.setColor(0x08FFFFFF);
        canvas.drawRect(0, 0, width, height / 3, shinePaint);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) {
            windowManager.removeView(overlayView);
        }
    }
}
