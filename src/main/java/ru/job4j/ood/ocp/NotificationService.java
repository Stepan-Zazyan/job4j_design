package ru.job4j.ood.ocp;

public class NotificationService {
   //Допустим нам необходимо кроме отправки сообщения
    // по электронной почте отправлять еще смс сообщения.
    // в данном случае мы нарушим второй принцип,
    // потому что класс должен быть закрыт для модификации,
    // но открыт для расширения, а мы модифицируем (изменяем) метод - ошибка ocp.

    public void sendMessage(String typeMessage, String message) {
        if (typeMessage.equals("email")) {
           typeMessage = "email";
        }
        if (typeMessage.equals("sms")) {
            typeMessage = "sms";
        }

    }
}
