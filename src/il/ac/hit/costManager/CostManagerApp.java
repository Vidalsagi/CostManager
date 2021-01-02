package il.ac.hit.costManager;


import il.ac.hit.costManager.model.IModel;
import il.ac.hit.costManager.model.InMemoryModel;
import il.ac.hit.costManager.view.IView;
import il.ac.hit.costManager.view.View;
import il.ac.hit.costManager.viewmodel.IViewModel;
import il.ac.hit.costManager.viewmodel.ViewModel;

import javax.swing.*;

public class CostManagerApp {

    public static void main(String args[]) {

                    //creating the application components
                    IModel model = new InMemoryModel();
                    IView view = new View();
                    IViewModel vm = new ViewModel();

                    //connecting the components with each other
                    view.setViewModel(vm);
                    vm.setModel(model);
                    vm.setView(view);

    }
}

