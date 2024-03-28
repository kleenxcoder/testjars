package com.example.messageswebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.experimental.boot.server.exec.CommonsExecWebServerFactoryBean;
import org.springframework.experimental.boot.test.context.DynamicProperty;
import org.springframework.experimental.boot.test.context.EnableDynamicProperty;

@TestConfiguration
@EnableDynamicProperty
public class TestMessagesWebappApplicationMain {

    @DynamicProperty(name ="messages-api.uri", value = " 'http://localhost:' + port + '/hello'")
    @Bean
    static CommonsExecWebServerFactoryBean messagesApi() {
        return CommonsExecWebServerFactoryBean
                .builder()
                .mainClass("org.springframework.boot.loader.launch.JarLauncher")
                .classpath((cp) -> cp
                        .files("C:\\source\\git\\kleenxcoder\\testjars\\modules\\messages-api\\target\\messages-api-0.0.1-SNAPSHOT.jar")
//                        .files("../../messages-api/target/messages-api-0.0.1-SNAPSHOT.jar")
//                        .files("../../../../messages-api/target/messages-api-0.0.1-SNAPSHOT.jar")
                )
                ;
    }


    public static void main(String[] args) {
        SpringApplication
                .from(MessagesWebappApplication::main)
                .with(TestMessagesWebappApplicationMain.class)
                .run(args);
    }
}
