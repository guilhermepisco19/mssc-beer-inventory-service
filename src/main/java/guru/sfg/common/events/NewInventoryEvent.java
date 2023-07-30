package guru.sfg.common.events;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{
    @Serial
    private static final long serialVersionUID = 1029394920243896609L;

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
