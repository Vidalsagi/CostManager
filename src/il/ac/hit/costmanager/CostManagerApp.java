/*
Authors:

First Name: Sagi
Last Name: Vidal
ID: 205657042

First Name: Shlomi
Last Name: Amir
ID: 311403844
 */
package il.ac.hit.costmanager;

import il.ac.hit.costmanager.model.IModel;
import il.ac.hit.costmanager.model.DerbyDBModel;
import il.ac.hit.costmanager.view.IView;
import il.ac.hit.costmanager.view.View;
import il.ac.hit.costmanager.viewmodel.IViewModel;
import il.ac.hit.costmanager.viewmodel.ViewModel;

public class CostManagerApp {

    /**
     * Main program that will run the application
     * @param args
     */
    public static void main(String args[]) {
        /*
            Creating the app components and then connection component with each other
         */
                    //creating the application components
                    IView view = new View();
                    IModel model = new DerbyDBModel();
                    IViewModel vm = new ViewModel();
                    //connecting the components with each other
                    view.setViewModel(vm);
                    vm.setModel(model);
                    vm.setView(view);
    }
}

