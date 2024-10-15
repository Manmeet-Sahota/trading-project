package org;

import com.rabbitmq.client.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Consumer {

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMQUtil.getChannel();
        channel.queueDeclare(RabbitMQUtil.getQueueName(), false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");


            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Employee employee = new Employee();
            employee.setName(message);
            employee.setDepartment("Finance");
            employee.setSalary(50000.0);

            session.save(employee);
            transaction.commit();
            session.close();
        };

        channel.basicConsume(RabbitMQUtil.getQueueName(), true, deliverCallback, consumerTag -> { });
    }
}