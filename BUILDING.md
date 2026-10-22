# MiniWeather · Android 构建指南

## 开发环境

- Android Studio（支持 Android Gradle Plugin 9.4 的版本）
- JDK 17 或更高版本（CI 使用 JDK 17）
- Android SDK Platform 37（Android 17），Build Tools 36.0.0
- Gradle 9.8.2（通过项目自带的 Gradle Wrapper 下载）
- Linux、macOS 或 Windows

## 本地构建

在 Android Studio 中打开仓库根目录，配置 Android SDK，然后同步 Gradle；命令行运行：

```sh
./gradlew :app:assembleDebug :app:testDebugUnitTest
```

Windows 使用 `gradlew.bat`。调试 APK 位于 `app/build/outputs/apk/debug/`。
`local.properties` 是本机配置文件，不应提交；必要时设置 `sdk.dir`。

构建系统使用 Kotlin DSL (`*.gradle.kts`) 和
`gradle/libs.versions.toml` 统一管理插件/依赖版本。
仓库依赖源为 Google Maven、Maven Central 和 AutoBus 所需的 JitPack，
不再使用已退役的 JCenter。

## 兼容性边界

- `compileSdk = 37` 只表示使用 Android 17 SDK 编译。
- 暂时保留 `targetSdk = 29` 和 `minSdk = 23`：旧版应用仍以
  Android 10 的行为为目标，以免在没有设备测试的情况下改变定位权限和界面行为。
  **此 APK 不能作为符合 2026 年 Google Play 新应用及更新要求的正式发布包**。
  发布前需要单独将 targetSdk 提升到至少 36，并测试定位/权限、边到边布局及后台任务。
- 腾讯定位 SDK 与 AutoBus 版本仍遵循项目原先的集成，建议后续单独迁移和回归测试。
- 目前 GitHub Actions 检查 APK 编译和 JVM 单元测试，**不等价于真机/模拟器 UI、定位及网络集成测试**。

## Gradle Wrapper

`gradle/wrapper/gradle-wrapper.properties` 固定 Gradle 9.8.2 并校验官方 SHA-256。
历史版本的 Wrapper 引导 JAR/启动脚本仍保留在仓库中；具有本地 Gradle 构建环境后，
可运行 `./gradlew wrapper --gradle-version 9.8.2 --distribution-type bin`
再运行一次以更新 Wrapper JAR 与启动脚本，并独立提交生成的文件。
