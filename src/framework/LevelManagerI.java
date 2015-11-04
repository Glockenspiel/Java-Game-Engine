package framework;

 interface LevelManagerI {
	public void queueChangeLevel(Level level);
	public void doChangeLevel();
	public Level getCurrentLevel();
	public void setCurrentLevel(Level level);
	public void load();
	public void loadLatestState();
}
