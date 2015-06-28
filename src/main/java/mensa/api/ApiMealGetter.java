package mensa.api;

import mensa.api.hibernate.domain.*;

import java.util.HashSet;

import mensa.api.hibernate.HibernateUtil;

import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/meal/")
public class ApiMealGetter {
	
	@GET
	@Path("/{mealID:[0-9]*}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Meal getMealById(@PathParam("mealID") String mealID){
		
		createMockMenu();
		
		Meal meal = new Meal();
		meal.setName("asda");
		meal.setData(new MealData(meal, new Tags()));
		return meal;
		//return new Meal(Integer.parseInt(mealID));
	}
	
	public void createMockMenu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Tags tags1 = new Tags();	
		Meal meal1 = new Meal();
		meal1.setName("Spaghetti Con Carne");
		MealData data1 = new MealData(meal1, tags1);		
		meal1.setData(data1);
		

		Tags tags2 = new Tags();	
		Meal meal2 = new Meal();
		meal2.setName("Salat Gruen");
		MealData data2 = new MealData(meal2, tags2);		
		meal2.setData(data2);
		
		data1.setImages(new HashSet<Image>());
		data1.setRatings(new HashSet<Rating>());
		data2.setImages(new HashSet<Image>());
		data2.setRatings(new HashSet<Rating>());
		
		data1.getImages().add(new Image(1000, "testurl"));
		
		// ACTION

		session.save(data1);
		session.save(data2);
		session.save(meal1);
		session.save(meal2);
		
		Image image = new Image(1000, "testurl");
		image.setUrl("yoo1");
		session.save(image);
		image.setUrl("yoo2");
		
		// in mysql console:
		// use hibernatedb
		// show tables
		// show columns FROM tablename / describe tablename
		// select * from tablename
		session.save(new Image(1000, "testurl"));
		session.save(new Image(1000, "test2url"));

		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
}
