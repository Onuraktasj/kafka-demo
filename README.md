#Kafka Demo

Bu proje, Spring Boot ve Kafka kullanarak basit bir mesajlaşma sistemi oluşturmayı amaçlamaktadır. Kafka kullanarak olay tabanlı bir mimari ile mesaj alışverişi yapılmaktadır.

#Proje Yapısı

Proje, temel olarak üç ana bileşenden oluşmaktadır:

* **Order Service** Yeni siparişler oluşturur ve Kafka'ya bir olay yayınlar.

* **Inventory Service** Sipariş olaylarını dinler ve stok güncellemeleri yapar.

* **Notification Service** Sipariş durumlarını dinler ve ilgili bildirimleri gönderir.

#Gereksinimler

Projeyi çalıştırmadan önce aşağıdaki bağımlılıkların sisteminizde yüklü olduğundan emin olun:

Java 17+

Apache Kafka & Zookeeper

Docker (Opsiyonel, Kafka'yı çalıştırmak için önerilir)

Maven

#Kurulum ve Çalıştırma

**1. Kafka ve Zookeeper'ı Başlatma**

Kafka'yı çalıştırmak için Docker Compose kullanabilirsiniz. Eğer Kafka'yı Docker ile çalıştırmak istiyorsanız, aşağıdaki komutu kullanabilirsiniz:

cd kafka-docker
docker-compose up -d

Alternatif olarak, Kafka ve Zookeeper'ı manuel olarak çalıştırabilirsiniz:

bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

**2. Projeyi Derleme ve Çalıştırma**

Projeyi derleyip çalıştırmak için aşağıdaki adımları takip edebilirsiniz:

git clone https://github.com/Onuraktasj/kafka-demo.git
cd kafka-demo
mvn clean install
mvn spring-boot:run

Her servisi ayrı bir terminalde çalıştırmanız gerekmektedir.

**3. Kafka Konularını Oluşturma**

Aşağıdaki komutlarla Kafka konularını oluşturabilirsiniz:

bin/kafka-topics.sh --create --topic order-topic --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic notification-topic --bootstrap-server localhost:9092

#API Kullanımı

Projede örnek REST API uç noktaları bulunmaktadır. Sipariş oluşturmak için aşağıdaki örnek isteği kullanabilirsiniz:

POST http://localhost:8080/orders
Content-Type: application/json

{
    "orderId": "12345",
    "product": "Laptop",
    "quantity": 1
}

Bu işlem, order-topic üzerinden bir mesaj yayınlayacaktır. Daha sonra Inventory Service ve Notification Service bu mesajı işleyecektir.

#Log İzleme

Kafka mesajlarının işlendiğini görmek için aşağıdaki komutu kullanarak konuları dinleyebilirsiniz:

bin/kafka-console-consumer.sh --topic order-topic --from-beginning --bootstrap-server localhost:9092
bin/kafka-console-consumer.sh --topic notification-topic --from-beginning --bootstrap-server localhost:9092

#Katkıda Bulunma

Projeye katkıda bulunmak isterseniz, aşağıdaki adımları takip edebilirsiniz:

Bu repoyu fork'layın.

Yeni bir branch oluşturun (feature-xyz gibi).

Değişikliklerinizi yapın ve commit atın.

Pull Request (PR) gönderin.

