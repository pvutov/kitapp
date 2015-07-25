package mensa.api.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mensa.api.hibernate.HibernateUtil;
import mensa.api.hibernate.domain.ImageProposal;
import mensa.api.hibernate.domain.Meal;

import org.hibernate.Criteria;
import org.hibernate.Session;

@Path("/admin/inactiveImages/")
public class InactiveImages {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInactiveImages(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria inactiveImage = session.createCriteria(ImageProposal.class);

		List<ImageProposal> imageList = inactiveImage.list();
		Iterator<ImageProposal> it = imageList.iterator();
		
		ArrayList<ImageWithMealName> imgsWithNames = new ArrayList<ImageWithMealName>();
		Meal meal;
		ImageWithMealName currImgWithName;
		ImageProposal currImgProposal;
		while(it.hasNext()){
			currImgProposal = it.next();
			meal = (Meal) session.get(Meal.class, currImgProposal.getMealid());
			currImgWithName = new ImageWithMealName(currImgProposal.getUrl(), currImgProposal.getId(), meal.getName());
			imgsWithNames.add(currImgWithName);
		}
		
		return Response.ok(imgsWithNames).build();
	}
	
	private class ImageWithMealName{
		private String url;
		private int imageid;
		private String mealName;
		
		public ImageWithMealName(String url, int imageid, String mealName){
			this.url = url;
			this.imageid = imageid;
			this.mealName = mealName;
		}
		
		public String getUrl(){
			return url;
		}
		public int getImageid(){
			return imageid;
		}
		public String getMealName(){
			return mealName;
		}
		public void setUrl(String url){
			this.url = url;
		}
		public void setImageid(int imageid){
			this.imageid = imageid;
		}
		public void setMealName(String mealName){
			this.mealName = mealName;
		}
		
	}
}
