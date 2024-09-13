package template.example;

public class RegularPreparationTemplate extends RoomPreparationTemplate {
	
	@Override
	public void prepare() {
		System.out.println("Preparing Mini Fridge");
		System.out.println("Preparing Free Breakfast for 2");
	}
}
