package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Success_DecreasesTorpedoCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        boolean result = store.fire(2);

        // Assert
        assertEquals(true, result);
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_AlwaysFailing_ReturnsFalseAndKeepsTorpedoCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(false, result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_LastTorpedo_StoreBecomesEmpty() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act
        store.fire(1);

        // Assert
        assertEquals(true, store.isEmpty());
    }

    @Test
    void fire_ZeroTorpedos_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(2));
    }

    @Test
    void isEmpty_NotEmptyStore_ReturnsFalse() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.isEmpty();

        // Assert
        assertEquals(false, result);
    }

    @Test
    void isEmpty_EmptyStore_ReturnsTrue() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0);

        // Act
        boolean result = store.isEmpty();

        // Assert
        assertEquals(true, result);
    }
}
