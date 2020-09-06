public class PlayableEntitie extends Entitie implements MainCharacter {
    public PlayableEntitie(String s) {
        super(s);
    }

    @Override
    public void move(House h) throws HeIsThereException {
        if (this.getLocation().equals(h)) {
            throw new HeIsThereException("Герой уже там");
        }

        this.setLocation(h);
        System.out.println(this.getName() + ' ' + "пришел в " + h.getName());
    }

    @Override
    public void sit() {
    }

}
