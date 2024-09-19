@echo off
7za d target/gaon-2.0.21-jar-with-dependencies.jar org/apache/log4j/net/JMSAppender.class org/apache/log4j/net/SocketServer.class org/apache/log4j/net/JMSSink.class org/apache/log4j/jdbc/JDBCAppender.class org/apache/log4j/chainsaw/*
copy .\setting.yaml.sample .\target\