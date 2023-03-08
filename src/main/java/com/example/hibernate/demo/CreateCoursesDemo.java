package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
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

            // get the instructor from database
            int instructorPK = 1;
            Instructor instructor = session.get(Instructor.class, instructorPK);
            // create some courses
            Course course = new Course("Air Guitar - The Ultimate Guide");
            Course anotherCourse = new Course("The Pinball Masterclass");
            // add courses to instructor
            instructor.add(course);
            instructor.add(anotherCourse);
            // save the courses
            session.save(course);
            session.save(anotherCourse);
            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}
