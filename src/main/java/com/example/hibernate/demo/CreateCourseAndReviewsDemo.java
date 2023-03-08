package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import com.example.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {
    public static void main(String[] args) {

        // create session factory

        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                addAnnotatedClass(Review.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // create a course
            Course theCourse = new Course("How to block in Street Fighter - The Basics");
            // add some reviews
            theCourse.addReview(new Review("Great course!! Now I am a pro"));
            theCourse.addReview(new Review("Horrible, only works if you use control pad."));
            theCourse.addReview(new Review("Amazing course! I love Ryu"));
            // save the course ... and leverage the cascade all (i.e. save the reviews as well)
            System.out.println("Saving the course and the reviews...");
            System.out.println(theCourse);
            System.out.println(theCourse.getReviews());
            session.save(theCourse);
            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}
