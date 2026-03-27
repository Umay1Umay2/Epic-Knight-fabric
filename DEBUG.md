# Debug Guide - Epic Knights

This guide helps you troubleshoot issues with Epic Knights and collect diagnostic information.

## Table of Contents

1. [Enabling Debug Logging](#enabling-debug-logging)
2. [Collecting Logs](#collecting-logs)
3. [Common Issues](#common-issues)
4. [Diagnostic Reports](#diagnostic-reports)
5. [Reporting Issues](#reporting-issues)

## Enabling Debug Logging

### Configuration File

Edit `.minecraft/config/magistuarmory.json`:

```json
{
  "general": {
    "debugMode": true
  }
}
```

This will:
- Enable verbose logging
- Dump diagnostic information to `logs/epicknights-diagnostics.txt`
- Show additional trace information in console

### Environment Variables

Set environment variables before launching Minecraft:

```bash
# Linux/Mac
export FABRIC_LOADER_DEBUG=true
export EPIC_KNIGHTS_DEBUG=true

# Windows (Command Prompt)
set FABRIC_LOADER_DEBUG=true
set EPIC_KNIGHTS_DEBUG=true

# Windows (PowerShell)
$env:FABRIC_LOADER_DEBUG="true"
$env:EPIC_KNIGHTS_DEBUG="true"
```

## Collecting Logs

### Minecraft Crash Log

When the game crashes, Minecraft creates a crash report:

**Location**: `.minecraft/crash-reports/crash-YYYY-MM-DD_HH.MM.SS.txt`

**What to look for**:
- Exception type
- Stack trace
- Loaded mods list
- JVM version and arguments

### Latest Log

The most recent game session log:

**Location**: `.minecraft/logs/latest.log`

**How to collect**:
1. Enable debug mode (see above)
2. Launch Minecraft
3. Reproduce the issue
4. Close game
5. Attach `latest.log` to your issue

### Diagnostic Dump

When debug mode is enabled, Epic Knights creates a diagnostic file:

**Location**: `.minecraft/logs/epicknights-diagnostics.txt`

**Contents**:
- Mod version and configuration
- Loaded addons
- Registry contents
- System information
- Error stack traces

## Common Issues

### Issue: Mod Doesn't Load

**Symptoms**:
- Medieval items don't appear in creative
- Armor isn't available

**Troubleshooting**:

1. **Check dependencies**:
   ```bash
   # Verify these are in ~/.minecraft/mods/
   ls mods/ | grep -E "fabric-api|architectury|epic-knights"
   ```

2. **Check versions**:
   ```
   Required:
   - Minecraft 1.21.4 ✓
   - Fabric Loader 0.16.10+ ✓
   - Fabric API 0.112.2+1.21.4 ✓
   - Architectury 13.0.8+ ✓
   ```

3. **Review logs**:
   ```bash
   tail -50 ~/.minecraft/logs/latest.log | grep -i "magistu\|epic"
   ```

4. **Clear cache**:
   ```bash
   rm -rf ~/.minecraft/shadercache
   rm -rf ~/.minecraft/.cache
   ```

### Issue: Missing Textures/Models

**Symptoms**:
- Items have purple/black textures
- Models look incorrect

**Troubleshooting**:

1. **Verify assets are loaded**:
   - Check `.minecraft/assets/magistuarmory/`
   - Verify `models/` and `textures/` directories exist

2. **Resource pack issues**:
   - Check resource pack load order
   - Disable conflicting resource packs temporarily

3. **Regenerate assets**:
   ```bash
   # Delete generated resource data
   rm -rf ~/.minecraft/.cache
   rm -rf ~/.minecraft/shadercache
   
   # Relaunch Minecraft
   ```

### Issue: Crashes on Startup

**Symptoms**:
- Game crashes immediately when loading the mod
- Java exception visible in logs

**Troubleshooting**:

1. **Check system specs**:
   ```bash
   java -version
   # Should be Java 17+, preferably 21
   ```

2. **Verify mod compatibility**:
   - Remove optional mods (Better Combat, Epic Fight)
   - Test with only essential mods
   - Add mods back one by one

3. **Check memory allocation**:
   - Ensure launcher allocates enough RAM (2GB+ minimum, 4GB+ recommended)
   - Check JVM arguments: `-Xmx4G`

4. **Collect full crash report**:
   - Look in `.minecraft/crash-reports/`
   - Attachment the most recent crash report

### Issue: Poor Performance

**Symptoms**:
- Low FPS with Epic Knights installed
- Game stutters or freezes

**Troubleshooting**:

1. **Check system load**:
   ```bash
   # Monitor resource usage while running
   top              # Linux/Mac
   taskmgr.exe      # Windows
   ```

2. **Disable debug logging**:
   - Set `debugMode: false` in config
   - Verbose logging can impact performance

3. **Check for conflicts**:
   - Disable shader mods
   - Remove conflicting armor mods
   - Test with vanilla rendering

4. **Verify graphics settings**:
   - Reduce render distance
   - Lower shadow resolution
   - Disable fancy graphics if needed

## Diagnostic Reports

### Creating a Diagnostic Report

1. **Enable debug mode** (see above)
2. **Reproduce the issue**:
   - Load world
   - Perform actions that cause problem
   - Wait a few seconds
3. **Exit cleanly**:
   - Don't force-quit (allows logs to flush)
4. **Collect files**:
   ```bash
   # Create a diagnostic archive
   cd ~/.minecraft
   tar czf epic-knights-diagnostics.tar.gz \
     logs/latest.log \
     logs/epicknights-diagnostics.txt \
     crash-reports/ \
     config/magistuarmory.json
   
   # Windows: Create ZIP with above files
   ```

### What to Include in a Report

When reporting an issue, always include:

- [ ] Crash report (if applicable)
- [ ] Latest log file
- [ ] Diagnostic file (if debug mode enabled)
- [ ] Your mods list (`ls ~/.minecraft/mods/`)
- [ ] System specs (OS, Java version, RAM, GPU)
- [ ] Steps to reproduce
- [ ] Screenshots (if visual issue)

## Reporting Issues

### Creating a Good Issue Report

**Title**: Be specific
- ❌ Bad: "Mod doesn't work"
- ✅ Good: "Epic Knights items missing textures on Fabric 1.21.4"

**Description**:
```markdown
## Description
Brief description of the problem

## Steps to Reproduce
1. Do this
2. Then this
3. Issue occurs

## Expected Behavior
What should happen

## Actual Behavior
What actually happens

## System Information
- OS: Windows 10
- Java: OpenJDK 21.0.9
- Minecraft: 1.21.4
- Fabric Loader: 0.16.10
- Other mods: [list them]

## Logs and Diagnostics
[Attach crash report and latest.log]
```

### GitHub Issue Template

Use the provided template:
- Navigate to [Issues](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/issues)
- Click "New Issue"
- Select appropriate template
- Fill in required information

## Advanced Debugging

### Attaching a Debugger

For developers, you can attach a Java debugger:

```bash
export JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
./gradlew runClient
```

Then connect your IDE's debugger to `localhost:5005`.

### Profiling Performance

To identify performance bottlenecks:

```bash
./gradlew runClient --info 2>&1 | grep -i "epic\|armor\|render"
```

Use Java Flight Recorder or YourKit for detailed profiling.

### Tracing Addon Loading

Enable extra logging for addon system:

```bash
# In your launcher's JVM args
-Dcom.magistuarmory.addon.debug=true
```

## Getting Help

- **GitHub Issues**: [Report a bug](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/issues)
- **Discord**: [Join the community](https://discord.gg/H9CVcXEmYe)
- **Discussions**: [Ask a question](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/discussions)

---

**Remember**: The more detailed your report, the faster we can help! 🛡️⚔️
