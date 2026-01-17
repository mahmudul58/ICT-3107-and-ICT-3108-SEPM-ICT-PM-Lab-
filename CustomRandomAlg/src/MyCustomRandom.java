
public class MyCustomRandom {

    private static long seed = System.currentTimeMillis();

    private static final long MULTIPLIER = 6364136223846793005L;
    private static final long INCREMENT = 1442695040888963407L;

    public static void setSeed(long newSeed) {
        seed = newSeed;
    }

    private static long nextRaw() {

        seed = (seed * MULTIPLIER + INCREMENT);

        long x = seed;
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);

        return x;
    }

    public static int nextInt() {
        return (int) nextRaw();
    }

    public static int nextInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than Min");
        }
        long raw = nextRaw();
        // Mathematical absolute modulo to ensure positive range
        int range = max - min + 1;
        int offset = (int) (Math.abs(raw) % range);
        return min + offset;
    }

    public static double nextDouble() {
        long raw = nextRaw();
        // Mask to ensure positive and divide by max long value
        return (raw & Long.MAX_VALUE) / (double) Long.MAX_VALUE;
    }

    public static float nextFloat() {
        long raw = nextRaw();
        return (raw & Long.MAX_VALUE) / (float) Long.MAX_VALUE;
    }

    public static boolean nextBoolean() {
        return nextRaw() > 0;
    }
}