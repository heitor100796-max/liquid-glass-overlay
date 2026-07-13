# 💎 Liquid Glass Overlay

Um aplicativo Android que aplica um efeito elegante de vidro líquido (Liquid Glass) em todos os aplicativos, dando um visual moderno e similar ao iOS.

## ✨ Funcionalidades

- 🎨 **Efeito Liquid Glass** - Overlay com efeito de vidro translúcido
- 📱 **Compatível com Android 6.0+** - Funciona em praticamente todos os dispositivos
- 🎛️ **Controle fácil** - Liga/desliga com um switch
- 🚀 **Leve e rápido** - Não afeta o desempenho
- 🔄 **Executa em background** - Funciona continuamente

## 📋 Requisitos

- Android 6.0 (API 24) ou superior
- Permissão de **"Desenhar sobre outros aplicativos"**

## 🚀 Como usar

1. **Abra o aplicativo**
2. **Conceda a permissão** de overlay (se solicitado)
3. **Ative o switch** "Ativar Overlay"
4. **Pronto!** O efeito Liquid Glass estará ativo em todos os seus apps

## 🔧 Estrutura do Projeto

```
liquid-glass-overlay/
├── app/
│   ├── src/main/
│   │   ├── java/com/heitor/liquidglass/
│   │   │   ├── MainActivity.java          # Interface principal
│   │   │   └── OverlayService.java        # Serviço que cria o overlay
│   │   ├── res/
│   │   │   ├── layout/activity_main.xml   # Layout da UI
│   │   │   └── values/                    # Cores e strings
│   │   └── AndroidManifest.xml            # Configurações do app
│   └── build.gradle                       # Dependências
└── README.md
```

## 📝 Permissões necessárias

O aplicativo requer as seguintes permissões:

- `SYSTEM_ALERT_WINDOW` - Para desenhar por cima de outros apps
- `FOREGROUND_SERVICE` - Para manter o serviço ativo
- `POST_NOTIFICATIONS` - Para notificações (Android 13+)

## 🎨 Efeito Visual

O overlay cria um efeito de vidro líquido com:
- Linhas ondulantes animadas
- Transparência e brilho
- Efeito de gradiente suave

## ⚙️ Desenvolvimento

Para contribuir ou modificar o projeto:

1. Clone o repositório
2. Abra no Android Studio
3. Modifique o arquivo `OverlayService.java` para alterar os efeitos visuais
4. Compile e teste no seu dispositivo

## 🐛 Possíveis Problemas

**"Permissão não concedida"**
- Vá para Configurações → Aplicativos → Permissões → Desenhar sobre outros apps
- Procure por "Liquid Glass" e ative a permissão

**"O overlay não aparece"**
- Verifique se o serviço está rodando
- Reinicie o aplicativo
- Tente conceder a permissão novamente

## 📱 Compatibilidade

- ✅ Android 6.0 (API 24)
- ✅ Android 7.0 - 13 (APIs 24-33)
- ✅ Android 14+ (API 34+)

## 📄 Licença

Este projeto é de código aberto e está disponível para uso livre.

## 👨‍💻 Autor

Desenvolvido por **Heitor** para criar uma experiência Android mais elegante e moderna.

---

**Divirta-se com seu novo efeito Liquid Glass! ✨**
