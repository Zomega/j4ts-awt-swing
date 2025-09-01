package java.awt;

class MediaTracker {
	public MediaTracker(Component comp) {
		// TODO: Implement
	}

	public boolean checkAll() {
		return checkAll(false);
	}

	public boolean checkAll(boolean load) {
		return false; // TODO: Implement
	}

	public boolean isErrorAny() {
		return false;
	}

	public void waitForAll()
                throws InterruptedException {
        	// TODO: This probably won't work in JS. Replace usage.
        }

    public boolean waitForAll(long ms)
                   throws InterruptedException {
                   	        	// TODO: This probably won't work in JS. Replace usage.
                   	return false;
                   }

        public void addImage(Image image,
                     int id) {
        	// TODO: Implement
        }

        public void addImage(Image image,
                     int id,
                     int w,
                     int h) {
        	// TODO: Implement.
        }

        public int statusID(int id,
                    boolean load) {
        	return 0; // TODO: Implement
        }
}
