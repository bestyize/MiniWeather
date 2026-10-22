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
- `targetSdk = 36`（Android 16，符合 2026 年 Google Play 的目标 API 级别要求），
  `minSdk = 23`（Android 6）。**达到目标 API 要求不等于完成 Google Play 上架审核。**
- 定位改为用户在搜索城市页点击「当前位置」时才申请前台权限。Android 12+ 同时申请
  FINE/COARSE，允许只授权大致位置；用户拒绝后仍可手动搜索或添加城市。
  不再声明后台定位、电话状态或前台服务权限。
- 所有 Activity 采用 edge-to-edge 和系统栏/键盘 Insets 处理；使用系统默认返回导航
  支持 Android 16+ predictive back。
- 腾讯定位 SDK 与 AutoBus 版本仍遵循项目原先的集成，建议后续单独迁移和回归测试。
- 目前 GitHub Actions 检查 APK 编译和 JVM 单元测试，**不等价于真机/模拟器 UI、定位及网络集成测试**。

## Android 12–17 人工回归清单

建议至少使用 Android 12 (API 31)、Android 15 (API 35)、Android 16 (API 36)、
Android 17 (API 37)，并在一个 Android 6–11 设备上检查兼容性：

1. 首次启动：不自动弹出位置授权；可直接浏览/搜索/添加热门城市。
2. 搜索页点「当前位置」：首次请求同时列出精确与大致位置；授权精确或仅大致后，
   均能处理城市定位结果；拒绝时仍可手动搜索。
3. 连续拒绝后，在系统设置中开启位置权限，再点击当前位置能恢复定位。
4. 从城市搜索页用手势返回/系统返回键：城市管理页不能崩溃，不添加空城市。
5. 检查首页、城市管理和搜索页的顶部工具栏、底部列表、横竖屏及软键盘：
   不被状态栏、导航栏、挖孔区域或键盘挡住。
6. 测试自动/手动天气更新、城市管理和腾讯定位 SDK。天气服务与第三方 SDK
   的联网可用性需在实际设备和网络环境中验证。

若准备使用 `targetSdk = 37`，需再单独核查 Android 17 的新目标版本行为，
尤其是大屏尺寸适配与明文网络访问策略。

## Gradle Wrapper

`gradle/wrapper/gradle-wrapper.properties` 固定 Gradle 9.8.2 并校验官方 SHA-256。
历史版本的 Wrapper 引导 JAR/启动脚本仍保留在仓库中；具有本地 Gradle 构建环境后，
可运行 `./gradlew wrapper --gradle-version 9.8.2 --distribution-type bin`
再运行一次以更新 Wrapper JAR 与启动脚本，并独立提交生成的文件。
