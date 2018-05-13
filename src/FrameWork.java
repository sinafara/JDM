import javax.swing.*;
        import java.awt.*;
public class FrameWork extends JFrame
{
    private MenuBar menubar ;
    private Tulbar toolbar;
    public static Table tablebar;
    public FrameWork()
    {
        super("this is JDM");
        setLayout(new FlowLayout());
        menubar = new MenuBar();
        toolbar = new Tulbar();
        tablebar = new Table();
        setJMenuBar(menubar.MenuBar());
        add(toolbar.Tulbar());
       add(tablebar.Table());
    }


}