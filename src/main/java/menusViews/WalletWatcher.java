package menusViews;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import org.jetbrains.annotations.NotNull;

import MenuObjects.Alignment;
import MenuObjects.UINumberToggle;
import SpecifiedButtons.Number;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 18/09/2017.
 */
public class WalletWatcher {

    float xOffset=   -0.10f;
    float yOffset =  0.1f;

    UIDigit0 digit0;
    UIDigit1 digit1;
    UIDigit2 digit2;
    UIDigit3 digit3;
    UIDigit4 digit4;
    UIDigit5 digit5;

    LimitDigit0 limitDigit0;
    LimitDigit1 limitDigit1;
    LimitDigit2 limitDigit2;
    LimitDigit3 limitDigit3;
    LimitDigit4 limitDigit4;
    LimitDigit5 limitDigit5;

    public WalletWatcher()
    {
        digit0 = new UIDigit0(xOffset, yOffset,Alignment.TopRight,0.2f);
        digit1 = new UIDigit1(xOffset-0.05f, yOffset, Alignment.TopRight, 0.2f);
        digit2 = new UIDigit2(xOffset-0.10f, yOffset, Alignment.TopRight,0.2f);
        digit3 = new UIDigit3(xOffset-0.15f, yOffset, Alignment.TopRight,0.2f);
        digit4 = new UIDigit4(xOffset-0.20f, yOffset, Alignment.TopRight,0.2f);
        digit5 = new UIDigit5(xOffset-0.25f, yOffset,
                Alignment.TopRight,0.2f);

        limitDigit0= new LimitDigit0(xOffset-0.30f, yOffset,
                Alignment.TopRight,0.1f);
        limitDigit1= new LimitDigit1(xOffset-0.34f, yOffset,
                Alignment.TopRight,0.1f);
        limitDigit2= new LimitDigit2(xOffset-0.38f, yOffset,
                Alignment.TopRight,0.1f);
        limitDigit3= new LimitDigit3(xOffset-0.42f, yOffset,
                Alignment.TopRight,0.1f);
        limitDigit4= new LimitDigit4(xOffset-0.46f, yOffset,
                Alignment.TopRight,0.1f);
        limitDigit5= new LimitDigit5(xOffset-0.50f, yOffset,
                Alignment.TopRight,0.1f);

    }

    public class UIDigit0 extends Number
    {


        public UIDigit0(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.digits.length > 0 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.digits[SingletonObjects.player_money.digits.length-1] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }


        }


    }

    public class UIDigit1 extends Number
    {


        public UIDigit1(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.digits.length > 1 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.digits[SingletonObjects.player_money.digits.length-2] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }
        }
    }

    public class UIDigit2 extends Number
    {

        public UIDigit2(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.digits.length > 2 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.digits[SingletonObjects.player_money.digits.length-3] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }

        }

    }

    public class UIDigit3 extends Number
    {


        public UIDigit3(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.digits.length > 3 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.digits[SingletonObjects.player_money.digits.length-4] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }


        }


    }
    public class UIDigit4 extends Number
    {


        public UIDigit4(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.digits.length > 4 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.digits[SingletonObjects.player_money.digits.length-5] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }


        }


    }
    public class UIDigit5 extends Number
    {


        public UIDigit5(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.digits.length > 5 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.digits[SingletonObjects.player_money.digits.length-6] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }


        }


    }


    //////////////////WALLET LIMIT WATCHER
    public class LimitDigit0 extends Number
    {


        public LimitDigit0(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.wallet_digits.length > 0 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.wallet_digits[SingletonObjects.player_money.wallet_digits.length-1] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }


        }


    }


    public class LimitDigit1 extends Number
    {


        public LimitDigit1(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.wallet_digits.length > 1 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.wallet_digits[SingletonObjects.player_money.wallet_digits.length-2] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }
        }

    }

    public class LimitDigit2 extends Number
    {


        public LimitDigit2(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.wallet_digits.length > 2 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.wallet_digits[SingletonObjects.player_money.wallet_digits.length-3] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }


        }


    }

    public class LimitDigit3 extends Number
    {


        public LimitDigit3(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.wallet_digits.length > 3 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.wallet_digits[SingletonObjects.player_money.wallet_digits.length-4] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }


        }


    }


    public class LimitDigit4 extends Number
    {


        public LimitDigit4(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.wallet_digits.length > 4 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.wallet_digits[SingletonObjects.player_money.wallet_digits.length-5] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }


        }

    }


    public class LimitDigit5 extends Number
    {


        public LimitDigit5(float offsetright, float offsetup, @NotNull Integer alignment, float scale) {
            super(offsetright, offsetup, alignment, scale);
        }

        @Override
        public void update() {
            super.update();

            if(SingletonObjects.player_money.wallet_digits.length > 5 ) {
                int digit = Integer.parseInt(SingletonObjects.player_money.wallet_digits[SingletonObjects.player_money.wallet_digits.length-6] + "");
                this.setNumberVisibilty(digit);
            }
            else
            {
                this.setNumberVisibilty(0);
            }

        }

    }

}
