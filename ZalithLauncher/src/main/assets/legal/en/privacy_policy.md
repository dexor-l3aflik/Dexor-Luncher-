# LMC CRAFT Privacy Policy

**Project:** LMC CRAFT  
**Developer:** LMC CRAFT Project Maintainer (**we**, **us**, or **our**)  
**Version:** 1.0  
**Effective and last updated:** November 18, 2026

## 1. Introduction

This Privacy Policy explains how information is stored, processed, and transmitted when you use LMC CRAFT on Android. LMC CRAFT is an independent, unofficial modified launcher project. It is not affiliated with, endorsed by, or sponsored by Mojang Studios, Microsoft, or their affiliates.

LMC CRAFT is designed primarily to operate on your device. The current launcher implementation does not include an LMC CRAFT-operated user-account system, advertising SDK, or behavioral analytics SDK. Core local features do not require you to register with us.

LMC CRAFT is nevertheless a networked launcher. Authentication, searches, downloads, updates, multiplayer connectivity, optional log uploads, and external web pages require communication with third-party providers. Those providers can receive information such as your IP address and request metadata even when no information is sent to an LMC CRAFT-operated server.

Before first launching or using LMC CRAFT, you must read in full and expressly agree to both this Policy and the LMC CRAFT User Agreement. If you decline or do not agree to either document, the launcher will not allow you to enter or continue using LMC CRAFT or any of its features.

After either document is changed, the next time you open the app, the launcher will again display the full updated content of both documents and require your renewed explicit consent. If you decline or do not agree to either updated document, the launcher will not allow you to use LMC CRAFT.

## 2. Information Stored and Processed on Your Device

Depending on the features you use, LMC CRAFT stores or processes the following locally:

### 2.1 Settings and application data

- Launcher preferences, selected language-related settings, appearance, controls, download sources, and feature options;
- Game directory paths, installed-version information, Java runtimes, renderer and component configurations;
- Control layouts, imported files, downloaded resources, caches, skins, capes, and exported files; and
- Game saves, logs, mods, modpacks, resource packs, shaders, and other files that you create, import, or download.

### 2.2 Account information

For Microsoft/Minecraft accounts, LMC CRAFT may locally store your player name, profile UUID, Xbox user identifier, access token, refresh token, token expiry, account type, and downloaded skin or cape.

For a user-selected third-party authentication server, LMC CRAFT may locally store the server URL, account name or email, password, access token, client token, profile UUID, and related account data. The current account implementation retains the third-party server password so that the account can be used for authentication. You should use this option only with a server you trust and should protect access to your device.

Microsoft device-code authorization is handled through Microsoft services. LMC CRAFT does not ask for or receive your Microsoft account password. Microsoft may request and process information under its own privacy policy.

### 2.3 Diagnostics and logs

LMC CRAFT and the launched game can generate local launcher logs, native logs, crash reports, game logs, and multiplayer connection logs. These may contain:

- App and game versions, Android version, device model, CPU architecture, graphics and runtime information;
- Timestamps, errors, stack traces, performance or connection details;
- Player names, profile identifiers, file paths, server addresses, room codes, network addresses, and command or configuration details; and
- Other information written into logs by Minecraft, mods, runtimes, plugins, or the operating system.

Logs remain local unless you deliberately upload, export, or share them. Android's Logcat and diagnostic facilities may also make some information available to the operating system or to persons and tools with appropriate device access.

### 2.4 Microphone and sensor information

The microphone test reads audio samples locally to calculate and display a relative volume level. LMC CRAFT's microphone test does not save an audio recording or transmit the audio samples to us.

Gyroscope or motion-sensor input, touch input, and vibration features are processed on the device to provide game controls. LMC CRAFT does not upload this sensor input as part of those local control features.

## 3. Information Sent During Network Features

The data involved depends on your actions and selected providers. Network providers commonly receive your IP address, requested URL, time of request, and standard connection or HTTP metadata. We do not control their server logs or retention practices.

### 3.1 Microsoft, Xbox, and Minecraft services

When you add, refresh, validate, or use a Microsoft account, LMC CRAFT communicates with Microsoft, Xbox Live, and Minecraft/Mojang services. Requests can include device codes, OAuth tokens, Xbox tokens, player profile identifiers, game-entitlement queries, and skin or cape requests. These services process account information under their own terms and privacy policies.

When you play online, Minecraft, installed mods, and selected game servers may separately exchange account, gameplay, network, and device information. LMC CRAFT does not control those independent communications.

### 3.2 Third-party authentication servers

If you select a third-party authentication server, LMC CRAFT sends the account name or email and password you enter to that server for authentication. It also sends or validates access tokens and retrieves player profiles, skins, or capes as needed.

The server operator determines how that information is used and retained. LMC CRAFT permits user-configured server addresses and some content may use unencrypted HTTP if you configure or retrieve such an address. Use HTTPS and a trusted provider whenever possible.

### 3.3 Game, runtime, and content downloads

When you browse versions, search for content, install a game or mod loader, check a mod for updates, or download a resource, LMC CRAFT may send version numbers, game or mod-loader selections, project or file identifiers, search terms, installed-mod identifiers and versions, architecture information, and download requests to the relevant source.

Sources can include:

- Mojang/Minecraft distribution services;
- Forge, NeoForge, Fabric, Quilt, Legacy Fabric, OptiFine, Maven repositories, and other runtime or library sources;
- Modrinth and CurseForge, including their content-delivery services;
- BMCLAPI, MCIM Mirror, Tencent Maven mirrors, GT New Horizons repositories, and other mirrors or community sources; and
- Custom URLs and sources selected by you or contained in content you import.

Downloaded or imported third-party content can make its own network connections after installation. Its behavior is not covered by our promises about LMC CRAFT itself.

### 3.4 Update checks and project information

When LMC CRAFT checks for launcher updates or retrieves project information such as sponsor data, it may contact GitHub APIs and content services. For Chinese-language environments, update retrieval may fall back to `repo.miawa.cn` if the GitHub request fails.

An update check can reveal the requested project resource and standard network metadata; the current app version is compared locally with retrieved release information. Selecting an update, plugin, cloud-drive link, or external download opens or contacts the relevant third-party provider.

Language, locale, and time-zone settings can be used locally to select translations, regional mirrors, or fallback sources. LMC CRAFT does not request precise-location permissions for this purpose.

### 3.5 Optional log uploads

LMC CRAFT does not automatically upload crash reports or logs to us. If you explicitly choose to upload and share a log, its full contents are sent to the selected upload service:

- **mclo.gs** (`api.mclo.gs`); or
- **logshare.cn** (`api.logshare.cn`), which may be tried before mclo.gs in environments identified locally as Chinese mainland environments.

If the first attempted service fails, LMC CRAFT may retry with another listed service. An uploaded log may be assigned a link that others can open. Treat it as information shared with the service and anyone who receives the link.

Before uploading, review and remove sensitive information where possible. The upload service's own access, retention, and deletion rules apply. We may be unable to delete a log from a third-party service on your behalf.

### 3.6 Multiplayer connectivity

When you use multiplayer connectivity, LMC CRAFT retrieves a node list from `terracotta.glavo.site` unless you configure a custom node. To host or join a room, the multiplayer networking components, selected or custom nodes, relays, and other participants may process:

- Room or invitation codes and player names;
- Machine or peer identifiers, IP addresses, ports, connection status, and routing metadata;
- Node addresses and diagnostic logs; and
- Network packets needed to establish and maintain the multiplayer connection.

The data path depends on the nodes and participants involved. Do not use this feature for unlawful activity or assume that it provides anonymity. Stop the connection when it is no longer needed.

### 3.7 Web pages, cookies, and external links

LMC CRAFT can display project pages or third-party content in an embedded WebView or open them in another app. The embedded WebView enables JavaScript and DOM storage and allows cookies, including third-party cookies, while the page is in use. Visited sites may therefore store identifiers and collect browsing and device information under their own policies.

When you leave LMC CRAFT's embedded WebView screen, LMC CRAFT destroys the WebView, clears its history, deletes its app-managed WebView cache directory, and requests removal of WebView cookies. This does not delete information already collected by the visited site or data stored by an external browser.

LMC CRAFT does not add its own advertising or tracking cookies to these pages.

### 3.8 DNS fallback

LMC CRAFT first uses the operating system's DNS resolver. If system DNS resolution fails, it may send the hostname being resolved through DNS over HTTPS to Cloudflare, Google Public DNS, or AliDNS. Successful fallback results are cached in memory for up to 10 minutes. These DNS providers receive the queried hostname and standard network metadata under their own policies.

## 4. Android Permissions and Device Features

Permissions are used for the following purposes:

| Permission or feature | Purpose |
|---|---|
| Internet and network-state access | Authentication, searches, downloads, updates, multiplayer, web pages, and connectivity checks |
| External/shared storage, including all-files access on supported Android versions | Manage game directories, saves, mods, runtimes, imported/exported files, and other user-selected content |
| Microphone | Calculate real-time volume for the optional microphone test; not used by LMC CRAFT to upload voice recordings |
| Notifications and foreground services | Show task, game, runtime, and multiplayer status and keep requested background work running |
| VPN service | Establish the multiplayer network tunnel after Android authorization |
| Wake lock | Keep relevant game or background tasks running when required |
| Vibration and motion sensors | Provide game-control feedback and optional motion-based controls |

Denying a permission may limit the related feature but does not prevent unrelated local features from working. You can change permissions in Android settings. Broad storage access can expose any file you choose to manage, so use it carefully and only with content you trust.

## 5. How Information Is Used

LMC CRAFT uses information locally and through the requested providers to:

- Authenticate accounts and maintain signed-in sessions;
- Install, configure, launch, and manage Minecraft and related content;
- Provide search, download, update, file-management, control, and multiplayer features;
- Display status, diagnose errors, and maintain local logs;
- Remember preferences and selected sources; and
- Provide the specific sharing or upload action you request.

The current LMC CRAFT app does not use your local account credentials, saves, logs, or files to build advertising profiles, sell personal information, or train an LMC CRAFT-operated profiling system.

## 6. When Information Is Shared

We do not sell or rent your personal information.

Information leaves your device in the circumstances described in Section 3, principally because you use a feature that communicates directly with a provider. If you voluntarily contact the project, such as by submitting a bug report or sending a message, the information you provide may be used to respond to your request or investigate the reported issue.

We may disclose information in our possession where required by applicable law, a valid legal process, or to protect the rights, safety, and security of users and the public.

Information already disclosed to a third-party provider, uploaded-log recipient, multiplayer participant, or external website is governed by that recipient's practices, not solely by this Policy.

## 7. Retention and Deletion

Local information is generally retained until you delete it, remove the relevant account, clear the app's data, or uninstall LMC CRAFT, subject to the following:

- Launcher logs are retained for **7 days by default**, with an available setting from **1 to 14 days**, and older logs are cleaned during logger initialization. Other game, native, crash, or multiplayer logs may follow separate lifecycle rules.
- Cached files may remain until replaced, cleared, or removed by Android or by you.
- Files in shared or external storage, exported content, and separately installed game files may remain after uninstallation.
- Removing an account from LMC CRAFT deletes its local database entry and associated local skin file; it does not revoke or erase data held by the account provider. Revoke sessions through that provider where necessary.

Android backup and device-transfer features are enabled for LMC CRAFT. Depending on your device and settings, app data may be copied by the operating system to a cloud-backup service or another device. Google, your device manufacturer, or another backup provider controls that processing under its own policy. Deleting local data may not immediately delete an existing system backup.

Third-party services determine their own retention periods. Contact the relevant provider to request deletion of server-side account data or uploaded logs.

## 8. Security

LMC CRAFT relies on Android's application sandbox and local storage controls and uses network-security mechanisms for supported connections. However, no application, device, network, or storage method is completely secure.

Protect your device with a screen lock, keep Android and LMC CRAFT updated, avoid untrusted modified builds and authentication servers, and do not share tokens or unreviewed logs. Third-party account credentials stored locally may be exposed if someone gains sufficient access to your app data, device backup, or a compromised device.

LMC CRAFT allows cleartext network traffic for compatibility with user-selected and third-party sources. Information sent over HTTP rather than HTTPS can be intercepted or modified. Prefer trusted HTTPS sources.

## 9. Your Choices and Rights

Within LMC CRAFT or Android, you can:

- View and remove locally saved accounts;
- Change download sources, custom authentication servers, and feature settings;
- Revoke microphone, storage, notification, or VPN permissions, subject to Android version and device controls;
- Choose whether to upload, export, or share logs and other files;
- Clear app data, delete local files, or uninstall LMC CRAFT; and
- Disable or manage system backup through your device or backup-provider settings.

Depending on your jurisdiction, you may also have rights to access, correct, delete, restrict, object to, or obtain a copy of personal information, and to withdraw consent. Because LMC CRAFT stores most information locally and does not maintain a general user-account database, you can exercise many of these choices directly on your device.

For information you have submitted directly to the project, contact us through the project repository. For information held by Microsoft, a custom authentication server, a content platform, a log-upload service, a multiplayer node, or another provider, direct your request to that provider. We may need enough information to verify a request, but do not send us your password or authentication tokens.

## 10. Children and Minors

LMC CRAFT is not designed to collect a child's information through an LMC CRAFT account because we do not provide such an account system. Nevertheless, account, online-game, multiplayer, and web features can involve personal information and third-party services.

If you are a minor, use LMC CRAFT only with a parent or legal guardian's consent and supervision. Parents and guardians should review account settings, downloads, shared logs, multiplayer activity, and third-party privacy policies. Age requirements and parental-consent mechanisms of Microsoft, Mojang, platform providers, and applicable law must also be followed.

If you believe a child has provided personal information directly to the project inappropriately, contact us through the project repository so the matter can be assessed.

## 11. International Processing

LMC CRAFT can contact providers and multiplayer participants in different countries or regions. Your IP address, account requests, downloads, uploaded logs, and multiplayer data may therefore be processed outside your place of residence. Privacy protections and government-access rules may differ between jurisdictions.

Third-party providers remain responsible for their own transfer mechanisms and legal obligations.

## 12. Changes to This Policy

We may update this Policy when LMC CRAFT's features, providers, or legal obligations change. The revised version will include a new date and version number and will be provided with LMC CRAFT or through the project repository.

After any change to this Policy or the User Agreement, the next time you open the app, the launcher will again display the full updated content of both documents. You must expressly agree to both updated documents again; if you decline or do not agree to either one, the launcher will not allow you to use LMC CRAFT.

## 13. Contact

For privacy questions, requests, or security reports, contact the LMC CRAFT project through:

- **Project:** https://github.com/dexor-l3aflik/Dexor-Luncher-
- **Issues:** https://github.com/dexor-l3aflik/Dexor-Luncher-/issues

Do not include passwords, access tokens, refresh tokens, or unreviewed logs in public issues.

This Policy is available in English. If additional translations are provided, they are intended to have the same meaning. If translations differ, the English version applies to the extent permitted by applicable law.