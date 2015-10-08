package interfaces;

import java.io.File;
import java.text.DecimalFormat;
import javafx.geometry.Insets;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;

public interface Constants {

    final String EMPTYS ="";     
    //For Newton's Second Law
    final double DEFAULTFORCE = 10.0;
    final double DEFAULTMASS = 10.0;
    
    //Numbers
    final int ZERO  = 0;
    final int ONE = 1;
    final double HALF =0.5;
    final int TWO = 2;
    final double TWO_D = 2.0;
    final int THREE = 3;
    final int FOUR = 4;
    final int FIVE = 5;
    final int TEN = 10;
    final double UNUSEDVALUE = Double.MAX_VALUE;
    
    //Mathematical Constants
    final double GRAVITY = 9.80665;
    final double SOSOUND = 340.29;
    final double TIMESTEP = 16.6666667;
    final double TIMESTEP_S = TIMESTEP / 1000.0;
    final double DEG_TO_RAD = Math.PI/180.0;
    
    //Decimal Formatting
    final DecimalFormat DF = new DecimalFormat("#.###");

    final int SCENEW = 1000;
    final int SCENEH = 700;

    //Graph constants
    final int GRAPHSECTIONW = SCENEW;
    final int GRAPHSECTIONH = SCENEH / 2;
    final double LINEWIDTH = 1;
    final Color GRAPHBGCOLOR = Color.BLACK;
    final Color FONTCOLOR = Color.BLACK;
    final double DATALINEWIDTH = 1;
    final Color GRAPHLINECOLOR = Color.WHITE;
    final Color GRAPHGRIDCOLOR = Color.rgb(60, 60, 60);
    final double AXISWIDTH = 35;
    final double TICKWIDTH = 5;
    final double TEXTHEIGHT = 8;
    final double TEXTPADDING = 4;
    final double GLOW1 = 1.5;
    final double GLOW2 = 2;
    final double GLOW3 = 4;
    final double GLOW4 = 7;
    final Color GRAPHGLOWCOLOR = Color.rgb(0, 255, 255, 0.15);
    final Color GRAPHDATACOLOR = Color.WHITE;
    final double DEFAULTGRAPHTICK = 1.0;
    final double DEFAULTGRAPHSCALE = 50.0;
    boolean USEGLOWLINES = true;

    final int ANIMATIONW = SCENEW / 2;
    final int ANIMATIONH = SCENEH - GRAPHSECTIONH;

    //UI Constants
    final ColumnConstraints COLCONSTRAINT = new ColumnConstraints(SCENEW / 2);
    final RowConstraints ROWCONSTRAINT = new RowConstraints(SCENEH / 2);

    final Color BLACKOPAQUE = Color.rgb(ZERO, ZERO, ZERO, 0.85);
    final Color BUTTONCOLOR = Color.WHITE;
    final CornerRadii BUTTONRADII = new CornerRadii(0.0);
    final Insets BUTTONINSETS = Insets.EMPTY;
    final Color UIBGCOLOR = Color.BLACK;
    final Background UIBG = new Background(new BackgroundFill(UIBGCOLOR, BUTTONRADII, BUTTONINSETS));
    final GaussianBlur TEXTBLUR = new GaussianBlur();
    final Color TEXTCOLOR = Color.WHITE;

    //IMAGES
    Image menuImage = new Image("/images/menugif.gif", 1000, 700, false, true);
    ImageView menuView = new ImageView(menuImage);
    Image background = new Image("/images/background2.png", 720, 720, true, true);
    Image grass = new Image("images/grass.png", 720, 720, true, true);
    Image flag = new Image("/images/cadFlag.gif", 50, 50, true, true);
    Image reticule = new Image("/images/reticule.gif", 50, 50, true, true);
    Image projectile = new Image("/images/projectile.png", 30, 30, true, true);
    Image mass = new Image("/images/mass.png", 50, 50, true, true);
    Image cannonBase = new Image("/images/cannon_base.png", 100, 100, true, true);
    Image cannon = new Image("/images/cannon.png", 100, 100, true, true);
    Image cart = new Image("images/cart.png", 200, 50, true, true);
    Image godHand = new Image("/images/handofgod.png", 250, 250, true, true);
    Image supermarket = new Image("/images/supermarket.jpg", SCENEW / 2, SCENEH / 2, false, true);
    Image golfBall = new Image("/images/ball.png", 25, 25, true, true);
    Image golfer = new Image("/images/golfer.png", 300, 150, true, true);
    Image golfer2 = new Image("/images/golfer2.png", 300, 150, true, true);
    Image bubble = new Image("/images/bubble.png", 100, 100, true, true);
    Image golfBG = new Image("/images/golfBackground.png", SCENEW / 2, SCENEH / 2, false, true);
    Image winBubble = new Image("/images/winBubble.png", 100, 100, true, true);
    Image golfFlag = new Image("/images/flagHole.gif", 150, 150, true, true);
    Image confetti = new Image("/images/confetti.gif", SCENEW / 2, SCENEH / 2, false, true);
    Image cadTire = new Image("/images/cadTire.jpg", SCENEW / 2, SCENEH / 2, false, true);
    Image bike = new Image("images/sportsbike.png", 50, 0, true, false);
    Image dollar = new Image("images/ctMoney.jpg", 60, 0, true, false);
    Image horn = new Image("/images/horn.png", 100, 0, true, true);
    Image person = new Image("/images/person.png", 50, 0, true, true);
    Image personSide = new Image("/images/person_side.png", 50, 0, true, true);

    //Helper Text
    final String[] helpText = {"Newton's Second Law states that Force is proportional to mass times acceleration (F=ma) \n"
        + "Therefore, by modifying the values of mass and force, you will notice a change in acceleration, as acceleration is proportional to Force over mass \n"
        + "You will notice that position is quadratic whereas velocity is linear. This is because velocity is the rate of change of position (also known as a derivative)\n"
        + "Therefore, it makes quite a lot of sense. Furtheremore, it makes even more sense that acceleration would be equal to a constant, since it is the rate of change\n"
        + "of velocity! Newton's second law is one of the most important laws of mechanics and physics, and it governs many systems that we know and use today. We will\n"
        + "leave you to make your own conclusions based on the graphs and on the visual feedback of the car moving with different mass and different force.\n"
        + "Recommended values : Force = 10Newtons Mass = 10kg",
        "It has been demonstrated that a mass attached to a spring and extended a certain distance\n"
        + "will experience a sinosoidal movement. This movement can be described as x = Asin(omega*t +phi) where A is the initial extension, also known as amplitude\n"
        + "omega is related to the period of the sinosoidal function. The period of a function is described as T = 2pi/omega. Phi is the time started in the period.\n"
        + "You will be able to see the sinosoidal nature of the movement both in the first graph and the animation of the mass attached to a spring. The second graph\n"
        + "also outlines the velocity (which is also sinosoidal!).\n"
        + "Recommended values, A=10m, omega = 2 rad, phi =0 ",
        "The New Sports Bike experiment is an example in optimization problems. You are tasked with finding the optimal price at which to sell a bike depending\n"
        + "on initial conditions that you setup. There is one variable out of your control, that is the selling factor of your bikes. Your sales are equal to the initial demand\n"
        + "minus a constant that is equal to 200 times the price (sales = initialDemand - 200*price). Your costs depend on your manufacturing cost and bike costs\n"
        + "(cost = manufacturingCost - bikeCost*unitSales). The gross revenue expected will be the price of the bikes times the units sold (revenue = price*sales).\n"
        + "From this, we can calculate a profit, which will be equal to a quadratic equation. From the graphs, you will be able to see at which price point your profit\n"
        + "will be maximized. However, know that you could not make any profit! The animation gives you a visual representation of the amount of bikes sold and the amount\n"
        + "of profit produced as the price inreases. Recommended values: Bike Cost: $110 Manufacturing Costs: $700000 Initial Demand:70000 units.",
        "Welcome to the Mathematical Golfing Course! We know you're excited to get to putting, but we must first explain some rules.\n"
        + "You see, the MGC is no regular golf course, we base our entertainemtn on math. Therefore, we have created golf balls that bounce according\n"
        + "to an infinite geometric series. \"What is an infinite geometric series?\" My,my, that is quite an embarassing thing to ask, but no worries\n"
        + "we are friendly to beginners. You see, an infinite geometric series is a sum of the form b*a^n from 0 to maxN. You will pick a base (a), a coefficient (b)\n"
        + "and a number of iterations (maxN), then your ball will travel as follows: b*(a^0 +a^1 +a^2 +a^3 +... +a^maxN).Now, since we are mathematicians, we have figured\n"
        + "out a formula for this, and it is of the form b*(1-a^n)/(1-a) +b. However, we will let you confirm that this is the correct formula.\n"
        + "By looking at the total displacement of your ball in the first graph and the individual steps at each iteration in the second.\n"
        + "Recommended Values: Iterations:10 Coefficient (b) = 50 Base (a):= 0.5",
        "Have you ever heard a car pass by you? Ever wondered why it makes that weird Vrooooooom noise? Well, the answer to your questions are here!\n"
        + "You see, the cause of this aud(ible) effect is due to the source of the sound moving, therefore adding speed to the speed of the sound itself\n."
        + "Now we all know that the for a wave frequency = velocity/lambda. So what happens when the car is moving? Well the wavelength is not changing, but\n"
        + "the speed of sound sure is! Therefore the frequency is also changing. Now depending on your own position and movement relative to the source you\n"
        + "will hear different effects. Now you might not have the equipent to test this out, so we've put at your disposal two useful graphs and a great animation!\n"
        + "From the first graph you will see the change in frequency of the sound as perceived by the observer, whereas the second graph will display the distance of\n"
        + "the source to the observer. You are able to control the actual frequency of the sound, the velocity of the source, the observer position, and the vertical distance\n"
        + "of the source as opposed to the observer. The circles appearing in the animation represent sound waves. Now, from our extensive reserach we've determined that\n"
        + "the perceived frequency of the sound of is equal to the actualFreqeuncy*speedSound/(speedSound+speedSource). We will leave it to you to confirm it! \n"
        + "Recommended Values: Frequency: 300 Velocity: 100 Distance: 75 Position: 400",
        "WELCOME SOLDIER, TO THE CANNON FIRING RANGE. TODAY'S LESSON, FIRE RANGE. NOW, AS YOU KNOW, OUR GOOD FRIEND NEWTON DISCOVERED GRAVITY, AND SINCE THEN\n"
        + "WE KNOW THAT CANNONBALLS SINK TO THE GROUND. NOW, ALTHOUGH THIS IS UNFORTUNATE, WE STILL HAVE THE TECHNOLOGY AND MATH TO FIRE OUR CANNONS AT LARGE DISTANCES \n"
        + "*cough* Being a drill sergent is tough on a man's voice. As I was saying, when firing a cannon ball, you will have control over two things: velocity and angle \n"
        + "Now, are genius mathematicians back in home base have found a theoretical formula to calculate the range of a cannonball fired at a velocity V at an angle Theta.\n"
        + "Range = Velocity^2 *sin(2*theta)/gravitationalConstant. Our mathematicians would like us to confirm their theory, and this is why you are here. Try different\n"
        + "angles and velocities and, with help from the graph of Vertical position and the graph of Velocity, confirm the findings of our researchers! We are counting on you\n"
        + "soldier! Recommended Values: Velocity:22m/s Angle:45 degrees."};

    final String introText = "Welcome, user, to Élie Harfouche's and Marco Purich's Integrative Project. Meant as a showcase of different scientific\n"
            + "and mathematical experiments and formulas, you will have the opportunity to try your hand at seeing science at work!\n"
            + "The tool bar gives you access to the experiments, categorized into their respective domains.\nThe options menu shows you the way out (don't leave us!)\n"
            + "and some display effects.\n\n When you pick your experiment, you will be brought to a page with input fields, graphs and an animation. If you are confused, click on the \"Help me!\" button\n"
            + "to receive helpful advice and a description of the experiment. Otherwise, enter your values, click play, and enjoy!\n\n"
            + "Enjoy your trip into the world of science and math!";
    
    final String[] allUnits = {"Time (s)", 
            "Position (m)", 
            "Velocity (m/s)", 
            "Acceleration (m/s^2)", 
            "Frequency (Hz)",
            "Distance (m)", 
            "Iteration", 
            "Current Value", 
            "Price ($)", 
            "Sales (Units)", 
            "Profits ($)",
            "Y Position (m)",
            "Y Velocity (m/s)"};
    
    final String[] allInputs = {"Frequency (Hz)", 
            "Velocity (m/s)", 
            "Perpendicular Distance (m)", 
            "Observer Position (m)", 
            "Iterations (n)", 
            "Coefficient (b)", 
            "Base (a)", 
            "Amplitude (m)", 
            "Omega (rad)", 
            "Phi (rad)", 
            "Bike Cost ($)", 
            "Manufacturing Cost ($)", 
            "Initial Demand (Units)", 
            "Mass (kg)", 
            "Force (N)", 
            "Angle (degrees)"};

    //For Doppler Effect
    final double DOPPLER_BASELINE = 20;
    final double DOPPLER_PERSONX = 15;
    final double DOPPLER_PERSONY = 30;
    final double DOPPLER_SOUNDSCALE = 0.0166;
    final Color DOPPLER_RINGCOLOUR = Color.rgb(0, 0, 0, 1.0);
    final double DOPPLER_OPACITYOFFSET = 0.96;
    final int DOPPLER_RINGLIFE = 80;
    final double DOPPLER_FREQDIVISOR = 1500;

    //For Mass-Spring
    final double MASS_GRAPHRANGEX = 10;
    final double MASS_GRAPHOFFSETY = 2.5;
    final double MASS_MASSOFFSETX = 35;
    final double MASS_MASSOFFSETY = 25;
    final double MASS_SPRINGOFFSETX = 25;
    final double MASS_SPRINGY = 150;
    
    //Infinite Geometric Series
    /**
     * n = 20 a=0.6 b =100
     */
    final double INF_GOALGOLF = 100 * (0.6 * (1 - Math.pow(0.6, 20)) / (1 - 0.6)) + 100;
    final double INF_PRECISION =0.01;
    final double INF_FLAGOFFSET =30.0;
    final double INF_VELOCITY = 1/6.0;
    final double INF_TIMEGRAPH =20.0;
    
    //For New Sports Bike
    final double NSB_GRAPHRATIO = 1.5;
    final double NSB_CADTIREDOORS = 251.0;
    final int NSB_CASHFLOWPOSITIONINCREASE =5;
    final double NSB_RATEINCREASEVALUE =10.0;
    final int NSB_CONSTANT = 200;
    
    //For NSL 
    final double NSL_MAXTIMEGRAPH =20;
    final double NSL_ANIMATIONSCALE =10;
    final double NSL_ANIMATION_CART_HEIGHT_OFFSET =50;
    final double NSL_GODHAND_RATIO =((233.0 - 181.0) / 233.0);
    final String hellS = new File("hell.mp3").toURI().toString();
    final Media hell = new Media(hellS);
    
    //For ProjMotion
    final double PM_BACKGROUNDOFFSET =720;
    final double PM_GRASSOFFSET =700;
    final double PM_FLAGPOLEWIDTH =5;
    final double PM_FLAGPOLE_BASE =20;
    final double PM_CANNON_X_OFFSET =10;
    final double PM_CANNON_y_OFFSET =100;
    final double PM_FLAG_X_OFFSET=20;
    final double PM_CANNONBASE_X_OFFSET =-15;
    final double PM_CANNONBASE_Y_OFFSET =-80;
    final double PM_MAXANGLE =90 * DEG_TO_RAD;
    final double PM_OPTIMALANGLE =45* DEG_TO_RAD;
    final double PM_GRAPH_RATIO =1.5;
    final double PM_SCALE =8;
    final double PM_CANNONWIDTH =70;
    final double PM_CANNON_X_ROTATIONOFFSET =35;
    final double PM_CANNON_Y_ROTATIONOFFSET =49;
    
    //For Graph
    final double GRAPH_APPROXLINES = 10.0;
    final double GRAPH_LIMIT = 0.00001;
}