package com.company;

/*

2015 Override serialization
Make the runner thread continue to work after deserialization.
Runner object keywords cannot be modified.
Hint / Hint:
The constructor is not called during deserialization, only all fields are initialized.

Requirements:
1. The Solution class must support the Serializable interface.
2. The Solution class must support the Runnable interface.
3. The runner field in the Solution class must be declared with the transient modifier.
4. The readObject method must create a new Thread object with the current object as a parameter.
5. The readObject method must call the start method of a new Thread object.


 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
Переопределение сериализации
*/
public class Solution implements Serializable, Runnable {
    private transient Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        int count = 0;
        while (true) {
            System.out.println(++count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        runner = new Thread(this);
        runner.start();
    }

    public static void main(String[] args) {

    }
}



