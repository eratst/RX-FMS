FROM 172.19.0.253:20202/jtyk/java-base:1.0
RUN mkdir -p /home/apps/
COPY ./target/NewFactoryModel-4.9.2-SNAPSHOT-fat.jar /home/apps/
CMD java -Dfile.encoding=UTF8 -jar /home/apps/NewFactoryModel-4.9.2-SNAPSHOT-fat.jar