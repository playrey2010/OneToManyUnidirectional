package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {
    public static void main(String[] args) {

        // create session factory

        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get course from db

            // delete the course, then check instructor is still there
            // Air Guitar course has primary key of 10
            int coursePK = 10;
            Course courseToDelete = session.get(Course.class, coursePK);
            System.out.println("About to delete course: " + courseToDelete);
            session.delete(courseToDelete);
            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}
