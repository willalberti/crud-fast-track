Iniciar Zookeeper
root@willian-PC:/opt/kafka# bin/zookeeper-server-start.sh config/zookeeper.properties

Iniciar Kafka
root@willian-PC:/opt/kafka# bin/kafka-server-start.sh config/server.properties

CriarTopico
root@willian-PC:/opt/kafka# bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test

Producer
root@willian-PC:/opt/kafka# bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

Consumer
willian@willian-PC:/opt/kafka$ bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --group1

Listar Tópico (opcional)
root@willian-PC:/opt/kafka# bin/kafka-topics.sh --list --bootstrap-server localhost:9092

fontes:
https://emmanuelneri.com.br/2019/06/04/kafka-no-spring-boot/
https://kafka.apache.org/quickstart


