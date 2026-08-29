package views.core;

import java.util.List;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import views.config.UsersPanel;
import views.dashboard.DashboardPanel;
import views.events.EventsPanel;
import views.grades.GradesPanel;
import views.groups.GroupsPanel;
import views.incidents.IncidentsPanel;
import views.staff.StaffPanel;

public final class ViewRegistry {

    private static final List<ViewSpec> VIEWS = List.of(
            new ViewSpec("Inicio", "Inicio", MaterialDesignH.HOME, DashboardPanel::new),
            new ViewSpec("Incidencias", "Incidencias", MaterialDesignA.ALERT, IncidentsPanel::new),
            new ViewSpec("Eventos", "Eventos", MaterialDesignC.CALENDAR_MONTH, EventsPanel::new),
            new ViewSpec("Grados", "Grados", MaterialDesignA.ACCOUNT_SCHOOL, GradesPanel::new),
            new ViewSpec("Grupos", "Grupos", MaterialDesignA.ACCOUNT_GROUP, GroupsPanel::new),
            new ViewSpec("Personal", "Personal", MaterialDesignA.ACCOUNT, StaffPanel::new),
            new ViewSpec("Configuración", "Configuración", MaterialDesignC.COG, UsersPanel::new)
    );

    private ViewRegistry() {
    }

    public static List<ViewSpec> getAll() {
        return VIEWS;
    }
}