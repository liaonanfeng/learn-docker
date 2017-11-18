# learn-docker

- 如果Spring MVC代理了根路径（\)的url，在spring-mvc.xml增加配置项。
```
<!-- Enables swgger ui-->
<mvc:default-servlet-handler/>
<mvc:resources location="classpath:/META-INF/resources/" mapping="swagger-ui.html"/>
<mvc:resources location="classpath:/META-INF/resources/webjars/" mapping="/webjars/**"/>
```
>启动Tomat，通过localhost:8080/swagger-ui.html访问Swagger-UI。
- Maven插件生成API描述文档（json格式）
```
<plugin>
 <groupId>com.github.kongchen</groupId>
 <artifactId>swagger-maven-plugin</artifactId>
 <version>3.1.5</version>
 <configuration>
     <apiSources>
         <apiSource>
             <springmvc>true</springmvc>
             <locations>com.example.demo13</locations><!-- Controller所在的位置 -->
             <host>orderdish-ecom-web.51ping.com</host>
             <basePath>/s</basePath>
             <schemes>http,https</schemes>
             <info>
                 <title>${artifactId}</title>
                 <version>v1</version>
                 <description>Click Link Below for Help</description>
                 <termsOfService>http://www.github.com/kongchen/swagger-maven-plugin</termsOfService>
             </info>
             <!--html文档输出功能的模板文件 -->
             <!--<templatePath>${basedir}/src/test/resources/swagger_template/strapdown.html.hbs</templatePath>-->
             <!--html文档输出的位置 -->
             <!--<outputPath>${project.build.directory}/swagger-ui/swagger_document.html</outputPath>-->
             <swaggerDirectory>${project.build.directory}/swagger-ui</swaggerDirectory><!--定义API描述文档的输出目录 -->
             <outputFormats>yaml,json</outputFormats><!--支持yaml和json格式 -->
         </apiSource>
     </apiSources>
 </configuration>
 <executions>
     <execution>
         <phase>compile</phase>
         <goals>
             <goal>generate</goal>
         </goals>
     </execution>
 </executions>
</plugin>
```
> 执行mvn complie，在设置的target目录下会生成API描述文档。
- Maven插件生成静态文档（markdown格式）
```
<plugin>
 <groupId>io.github.swagger2markup</groupId>
 <artifactId>swagger2markup-maven-plugin</artifactId>
 <version>1.3.3</version>
 <configuration>
     <!--The URL or file path to the Swagger specification-->
     <swaggerInput>${project.build.directory}/swagger-ui/swagger.yaml</swaggerInput>
     <outputDir>${project.build.directory}/swagger-ui</outputDir>
     <outputFile>${project.build.directory}/swagger-ui/swagger.md</outputFile>
     <config>
         <!--设置输出文件的语言：ASCIIDOC, MARKDOWN, CONFLUENCE_MARKUP-->
         <swagger2markup.markupLanguage>MARKDOWN</swagger2markup.markupLanguage>
         <!--设置目录的展现方式-->
         <swagger2markup.pathsGroupedBy>TAGS</swagger2markup.pathsGroupedBy>
     </config>
 </configuration>
 <executions>
     <execution>
         <phase>compile</phase>
         <goals>
             <goal>convertSwagger2markup</goal>
         </goals>
     </execution>
 </executions>
</plugin>
```
>如果mvn complie时出现异常，可能是因为之前生成的yaml不符合Swagger规范。把yaml复制到Swagger Editor中，根据提示的语法错误修正，再手动执行mvn swagger2markup:convertSwagger2markup即可生成markdown文档。