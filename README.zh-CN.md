[English](README.md) | [**简体中文**](README.zh-CN.md)

# MiniWeather · Mini天气

[![Android 构建](https://github.com/bestyize/MiniWeather/actions/workflows/android.yml/badge.svg?branch=master)](https://github.com/bestyize/MiniWeather/actions/workflows/android.yml)
[![许可证：Apache-2.0](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](LICENSE)

MiniWeather（Mini天气）是由 **亦泽（Yize）** 开发的轻量级 Android 天气应用。应用通过腾讯天气网页接口获取天气数据，为已添加的城市展示天气实况、预报及相关指数。

> **项目现状：** 项目在 2026 年升级了 Android 构建体系，可以使用现代 Android 工具链编译。但旧版天气接口和腾讯定位 SDK 尚需在真实设备及网络环境中完成回归测试，不能将当前 Debug APK 视为已经完成正式发布验收的安装包。

## 效果预览

![Mini天气界面截图](pic/show.png)

更多截图：[天气首页](pic/main_top.jpg) · [预报](pic/main_mid.jpg) · [天气详情](pic/main_last.jpg) · [城市管理](pic/manage.jpg)

## 主要功能

- 展示实时天气、温度、风况、空气湿度、大气压和空气质量。
- 展示逐小时、多日天气预报，以及生活和天气指数。
- 支持搜索城市、选择热门城市和管理多个已添加的城市。
- 使用 SQLite 在本地保存城市信息。
- **前台按需定位：** 仅在城市搜索页点击当前位置后才请求定位权限。Android 12 及以上系统允许选择大致位置；即使拒绝授权也可以手动搜索城市。
- 项目基于 Java 和 Android XML 布局实现，无需转换为 Kotlin 或 Jetpack Compose 即可参与维护。

实际天气和定位结果依赖第三方网络服务。

## Android 构建环境

| 组件 | 当前项目配置 |
| --- | --- |
| Android Gradle Plugin | 9.4.0 |
| Gradle | 9.8.2（Wrapper） |
| Java | JDK 17 |
| compileSdk | API 37（Android 17） |
| targetSdk | API 36（Android 16） |
| minSdk | API 23（Android 6.0） |
| Android SDK | Platform 37.0；Build Tools 36.0.0 |

推荐使用支持以上 Android Gradle Plugin 的 Android Studio。项目已采用 **Gradle Kotlin DSL**（`*.gradle.kts`）和 **Version Catalog**（`gradle/libs.versions.toml`），统一管理构建配置和依赖版本。

## 本地编译

1. 安装 JDK 17、上述 Android SDK Platform 和兼容的 Android Studio。
2. 克隆并使用 Android Studio 打开仓库根目录，或在命令行运行：

   ```bash
   git clone https://github.com/bestyize/MiniWeather.git
   cd MiniWeather
   ./gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
   ```

   Windows 用户使用 `gradlew.bat` 代替 `./gradlew`。

3. Debug APK 输出在 `app/build/outputs/apk/debug/`。

可以通过本机的 `local.properties`（`sdk.dir`）或 Android SDK 环境变量配置 SDK 路径。**不要将 `local.properties` 或签名密钥提交到仓库。**

GitHub Actions 在推送代码和发起 PR 时会执行 Debug APK 构建、JVM 单元测试和 Android Lint。成功的工作流运行会上传 **Debug APK 构建产物**，并不代表已经发布正式签名的安装包。

更详细的环境配置、兼容性注意事项和真机回归清单请参阅 [BUILDING.md](BUILDING.md)。

## 数据来源与技术说明

源码目前使用以下腾讯天气**网页接口**：

- 天气查询：`https://wis.qq.com/weather/common`
- 城市搜索：`https://wis.qq.com/city/like`

项目还使用腾讯定位 SDK、OkHttp、Gson、Glide、AndroidX 和 AutoBus。上述网页接口**不保证长期稳定，也不能视为官方面向第三方开发者提供的稳定接口**；可用性、响应格式、天气图标地址和 SDK 行为均可能发生变化。

历史研究资料可查看[腾讯天气 API 笔记](%E8%85%BE%E8%AE%AF%E5%A4%A9%E6%B0%94API.md)和[原版 README 完整归档（包含大量 JSON 响应示例）](docs/legacy/README-original.zh-CN.md)。这些内容是历史记录，**不代表已经核实过的最新 API 规范**。

## 权限与兼容性

- 只有主动使用当前位置时，应用才申请前台精确/大致定位权限；手动添加城市不需要定位授权。
- 应用没有声明后台定位或读取电话状态权限。
- 已在代码层面处理 Android 12–17 的系统栏、软键盘 Insets 和标准返回导航；**仍需要真机或模拟器回归测试**。
- `targetSdk = 36` **不等于**保证通过 Google Play 审核，也不代表第三方天气服务一定可用。

## 参与贡献

欢迎通过 [Issues](https://github.com/bestyize/MiniWeather/issues) 反馈问题，或通过 [Pull Requests](https://github.com/bestyize/MiniWeather/pulls) 提交修复。报告崩溃或定位异常时，建议提供 Android 版本、设备型号、复现步骤和必要日志；请勿提交密钥或个人精确位置信息。

## 开源许可

项目采用 [Apache License 2.0](LICENSE)。

---

[English](README.md) | [**简体中文**](README.zh-CN.md)
