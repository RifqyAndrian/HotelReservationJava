package template.example;

public class FamilyPreparationTemplate extends RoomPreparationTemplate {
	
	@Override
	public void prepare() {
		System.out.println("Preparing Private Jacuzzi");
		System.out.println("Preparing Free Breakfast for 3");
		System.out.println("Preparing Dinner Menu");
	}
}
