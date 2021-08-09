通过 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP

1、引入 byte-buddy 和 byte-buddy-agent
```java
<!-- byteBuddy 字节码编程 -->
<dependency>
    <groupId>net.bytebuddy</groupId>
    <artifactId>byte-buddy</artifactId>
    <version>LATEST</version>
</dependency>

<!-- https://mvnrepository.com/artifact/net.bytebuddy/byte-buddy-agent -->
<dependency>
    <groupId>net.bytebuddy</groupId>
    <artifactId>byte-buddy-agent</artifactId>
    <version>LATEST</version>
</dependency>
```

2、打包的时候指定 Premain-Class 
```java
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <transformers>
            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                <manifestEntries>
                    <Main-Class>week5.problem45.OceanService2</Main-Class>
                    <Premain-Class>week5.problem45.MyAgent</Premain-Class>
                </manifestEntries>
            </transformer>
        </transformers>
        <artifactSet>
            <includes>
                <include>net.bytebuddy:byte-buddy:jar:</include>
                <include>net.bytebuddy:byte-buddy-agent:jar:</include>
            </includes>
        </artifactSet>
    </configuration>
</plugin>
```

3、idea启动项目时指定 -javaagent:/Users/ocean_wll/IdeaProjects/geekbang_java_up_study/work/target/work-1.0-SNAPSHOT.jar 
