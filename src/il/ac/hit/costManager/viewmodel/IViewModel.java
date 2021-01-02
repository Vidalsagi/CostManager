package il.ac.hit.costManager.viewmodel;

import il.ac.hit.costManager.view.IView;
import il.ac.hit.costManager.model.IModel;

public interface IViewModel {

    public void setView(IView view);
    public void setModel(IModel model);

}
