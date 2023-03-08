package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // get the instructor detail
            int primaryKey = 1555;
            // get Instructor Detail
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, primaryKey);
            // print
            System.out.println("instructorDetail: " + instructorDetail);

            System.out.println("Associated instructor object: " + instructorDetail.getInstructor());



            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }


    }
}
