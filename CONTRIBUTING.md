# Contributing to Epic Knights

Thank you for your interest in contributing to Epic Knights! This document provides guidelines for contributing to the project.

## Code of Conduct

- Be respectful and inclusive
- Report issues promptly
- Discuss problems before implementing large changes
- Credit original authors when using code

## How to Contribute

### 1. Report Issues

Found a bug or have a suggestion?

1. Check [existing issues](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/issues) first
2. Create a [new issue](https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4/issues/new) with:
   - Clear title
   - Detailed description
   - Steps to reproduce (for bugs)
   - Your Minecraft version and mod list
   - Relevant logs

### 2. Fork and Clone

```bash
# Fork the repository on GitHub, then:
git clone https://github.com/YOUR_USERNAME/Epic-Knights_v1.21.4.git
cd Epic-Knights_v1.21.4
git remote add upstream https://github.com/phantomhivealice200-stack/Epic-Knights_v1.21.4.git
```

### 3. Create a Feature Branch

```bash
git checkout -b feature/your-feature-name
# or
git checkout -b bugfix/issue-number
```

Branch naming:
- `feature/*` - New features
- `bugfix/*` - Bug fixes
- `docs/*` - Documentation
- `addon/*` - Addon implementations
- `test/*` - Test improvements

### 4. Make Changes

#### Code Style

- Use 4 spaces for indentation
- Use meaningful variable names
- Keep methods focused (single responsibility)
- Add javadoc comments for public methods
- Follow Java naming conventions

Example:

```java
/**
 * Registers an item with the addon registry.
 * 
 * @param name the item name
 * @param item the Item instance to register
 * @return the registered item
 */
public Item registerItem(String name, Item item) {
    Objects.requireNonNull(name, "Item name cannot be null");
    Objects.requireNonNull(item, "Item cannot be null");
    // Implementation
    return item;
}
```

#### File Organization

- Keep related code together in packages
- One public class per file (usually)
- Use interfaces for contracts
- Place tests in corresponding package structure

### 5. Test Your Changes

```bash
# Run all tests
./gradlew test

# Run specific test
./gradlew test --tests AddonLoaderTest

# Build the project
./gradlew clean build

# Test in-game
./gradlew runClient
```

### 6. Commit Changes

Write clear, descriptive commit messages:

```bash
git add .
git commit -m "feat: add new armor type - Dragon Plate

- Adds new armor material with custom stats
- Includes models and textures
- Updated localization strings
- Closes #123"
```

Commit message format:
- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation
- `test:` - Test additions
- `refactor:` - Code refactoring
- `perf:` - Performance improvements
- `chore:` - Maintenance

### 7. Push and Create Pull Request

```bash
# Keep your branch up to date
git fetch upstream
git rebase upstream/fabric-1.21.4-port

# Push your changes
git push origin feature/your-feature-name

# Create PR on GitHub
# - Use the PR template
# - Link related issues
# - Request reviewers
```

### Pull Request Guidelines

Your PR should:
- ✅ Have a clear title and description
- ✅ Reference related issues (`fixes #123`)
- ✅ Pass all CI checks
- ✅ Include tests for new functionality
- ✅ Update documentation if needed
- ✅ Follow the code style

Examples of good PR titles:
- `feat: add new shield variants for 1.21.4`
- `fix: resolve armor rendering bug on specific models`
- `docs: update addon development guide with examples`
- `test: improve coverage for AddonLoader`

## Development Guidelines

### What to Work On

Good first contributions:
- 🐛 Bug fixes with clear reproduction steps
- 📝 Documentation improvements
- ✅ Writing unit tests
- 🎨 Code style/organization improvements
- 🔧 Addon examples and documentation

Before starting larger features:
- 💬 Discuss in an issue first
- Get approval from maintainers
- Outline your approach

### Addon Development

If you're developing an addon:

1. Follow [ADDON_DEVELOPMENT.md](./ADDON_DEVELOPMENT.md)
2. Place addon code in `examples/addon-*` directory
3. Include comprehensive documentation
4. Test thoroughly with Epic Knights
5. Submit as example or separate repository

### Documentation

When documenting:
- Use clear, concise language
- Include code examples
- Add table of contents for long docs
- Keep examples up-to-date
- Link to related resources

README example:
```markdown
## Configuration

Epic Knights provides configuration via Cloth Config.

**File location**: `.minecraft/config/magistuarmory.json`

**Example**:
```json
{
  "general": {
    "enableAllFeatures": true
  }
}
```
```

## Building and Testing

### Build Commands

```bash
# Full build
./gradlew clean build

# Build without tests
./gradlew build -x test

# Run specific task
./gradlew :common:compileJava

# Clean build artifacts
./gradlew clean
```

### Testing

```bash
# Run unit tests
./gradlew test

# Run tests with coverage (if configured)
./gradlew test --info

# Test in Minecraft
./gradlew runClient
```

### Debugging

Enable debug logging:
```bash
./gradlew runClient --debug
```

## Porting Checklist

If adding features or fixing bugs for compatibility:

- [ ] Code compiles without warning
- [ ] Unit tests pass
- [ ] Game doesn't crash on startup
- [ ] New items/blocks appear correctly
- [ ] Existing functionality still works
- [ ] No performance regression
- [ ] Documentation updated
- [ ] Changelog updated (if applicable)

## Reviewers and Maintainers

- @phantomhivealice200-stack - Project owner
- Original mod author: @Magistu

## License

By contributing to Epic Knights, you agree to license your contributions under the same license as the project.

All Rights Reserved - See LICENSE file for details

## Questions?

- Create an issue with the `question` label
- Join our [Discord server](https://discord.gg/H9CVcXEmYe)
- Check existing documentation

---

Thank you for contributing to Epic Knights! 🛡️⚔️
