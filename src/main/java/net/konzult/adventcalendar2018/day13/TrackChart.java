package net.konzult.adventcalendar2018.day13;

import java.util.*;

import static net.konzult.adventcalendar2018.day13.TrackType.*;

public class TrackChart {
    public static final int MAX_TICK = 100000;
    private final Map<Coordinates, Track> trackMap = new HashMap<Coordinates, Track>();
    private final List<Cart> carts = new ArrayList<>();
    private Coordinates firstCrashPosition = null;

    public void parseTrack(List<String> trackList) {
        trackMap.clear();
        carts.clear();
        int cartId = 0;
        for (int y = 0; y < trackList.size(); y++) {
            String s = trackList.get(y);
            for (int x = 0; x < s.length(); x++) {
                Coordinates position = new Coordinates(x, y);
                Coordinates direction;
                char symbol = s.charAt(x);
                switch (symbol) {
                    case '/':
                        addTrack(position, NEGATIVE_SWAP);
                        break;
                    case '\\':
                        addTrack(position, POSITIVE_SWAP);
                        break;
                    case '+':
                        addTrack(position, CROSSING);
                        break;
                    case '|':
                    case '-':
                        addTrack(position, STRAIGHT);
                        break;
                    case '>':
                        addTrack(position, STRAIGHT);
                        direction = new Coordinates(1, 0);
                        carts.add(new Cart(cartId++, position, direction));
                        break;
                    case '<':
                        addTrack(position, STRAIGHT);
                        direction = new Coordinates(-1, 0);
                        carts.add(new Cart(cartId++, position, direction));
                        break;
                    case 'v':
                        addTrack(position, STRAIGHT);
                        direction = new Coordinates(0, 1);
                        carts.add(new Cart(cartId++, position, direction));
                        break;
                    case '^':
                        addTrack(position, STRAIGHT);
                        direction = new Coordinates(0, -1);
                        carts.add(new Cart(cartId++, position, direction));
                        break;
                }

            }

        }
    }

    private void addTrack(Coordinates coordinates, TrackType trackType) {
        Track track = new Track(coordinates, trackType);
        trackMap.putIfAbsent(coordinates, track);
    }

    public Map<Coordinates, Track> getTrackMap() {
        return trackMap;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public Coordinates tick() {
        carts.stream().sorted(Comparator.comparing(Cart::getPosition))
                .forEach(cart -> {
                    TrackType trackAhead = trackMap.get(cart.getPosition().add(cart.getDirection())).getTrackType();
                    cart.tick(trackAhead);

                    Cart crashingCart = findFirstCrashingCart(cart);
                    if (crashingCart != null) {
                        if (firstCrashPosition == null ) {
                            firstCrashPosition = cart.getPosition();
                        }
                        carts.remove(cart);
                        carts.remove(crashingCart);
                    }
                });

        return null;
    }

    private Cart findFirstCrashingCart(Cart cart) {
        return carts.stream().filter(otherCart
                -> !otherCart.equals(cart) && otherCart.getPosition().equals(cart.getPosition()))
                .findFirst().orElse(null);
    }

    public Coordinates getFirstCrashPosition() {
        return firstCrashPosition;
    }

    public Coordinates tickUntilCrash() {
        for (int i = 0; i < MAX_TICK && firstCrashPosition== null; i++) {
            tick();
        }
        return firstCrashPosition;
    }

    public Coordinates tickUntilLastRemains() {
        for (int i = 0; i < MAX_TICK && carts.size() > 1; i++) {
            tick();
        }
        if (carts.size() == 1)
            return carts.get(0).getPosition();
        else return null;
    }
}
