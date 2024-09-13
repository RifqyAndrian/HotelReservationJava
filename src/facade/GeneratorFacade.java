package facade;

public class GeneratorFacade {
	private CancellationGenerator cancellationGenerator;
	private UpgradeGenerator upgradeGenerator;
	
	public GeneratorFacade() {
		this.cancellationGenerator = new CancellationGenerator();
		this.upgradeGenerator = new UpgradeGenerator();
	}
	
	public void generateCancelPDF() {
		cancellationGenerator.generate();
	}
	
	public void generateUpgradePDF() {
		upgradeGenerator.generate();
	}

}
