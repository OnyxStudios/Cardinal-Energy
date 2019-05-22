# Cardinal Energy

An Energy API for Fabric

Cardinal Energy depends on `Cardinal Components API`, be sure to depend on it or jar-in-jar it

```
repositories {
    maven {
        name = "NerdHubMC"
        url = "https://maven.abusedmaster.xyz"
    }
}

dependencies {
    modCompile "com.github.NerdHubMC:Cardinal-Energy:${cardinal_version}"
    modCompile "com.github.NerdHubMC:Cardinal-Components-API:${cardinal_components}"
    include "com.github.NerdHubMC:Cardinal-Components-API:${cardinal_components}"
}
```