package main.java.ch.uzh.board;

public enum GridType {
  TARGET_GRID,
  OCEAN_GRID,
  CHEAT_GRID;

  @Override
  public String toString() {
    String title = "Unknown";
    switch (this) {
      case TARGET_GRID:
        title = "Target Grid";
        break;
      case OCEAN_GRID:
        title = "Ocean Grid";
        break;
      case CHEAT_GRID:
        title = "Cheat Grid";
    }
    return title;
  }
}
