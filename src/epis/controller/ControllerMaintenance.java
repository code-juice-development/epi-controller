package epis.controller;

import epis.models.DataObject;
import epis.utils.MessageUtil;
import epis.view.Observer;
import epis.view.ViewEdit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Controller Maintenance Base
 * @author Jean Poffo
 * @since  30/08/2020
 */
public abstract class ControllerMaintenance implements Observed {
    
    protected List<Observer> observers = new ArrayList();
    
    protected List<DataObject> data = new ArrayList();

    public ControllerMaintenance() {
        this.processUpdateData();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyUpdate() {
        this.observers.forEach(observer -> observer.update());
    }
    
    public List<DataObject> getData() {
        return data;
    }

    public void setData(List<DataObject> data) {
        this.data = data;
    }
    
    public abstract void processUpdateData();
    
    public abstract ViewEdit getViewEdit(DataObject entity, ProcessAction process);
    
    public abstract DataObject getDataObjectModel();
    
    public void callActionAdd() {
        this.getViewEdit(this.getDataObjectModel(), this.getProcessActionAdd()).setVisible(true);
    }
    
    public void callActionEdit(int selectedItem) {
        if(this.validSelectedItem(selectedItem)) {
            this.getViewEdit(this.getData().get(selectedItem), this.getProcessActionEdit()).setVisible(true);
        }
    }
    
    public void callActionDelete(int selectedItem) {
        if(this.validSelectedItem(selectedItem) && MessageUtil.question("Deseja realmente excluir o registro?", "Aviso")) {
            this.processActionDelete(this.getData().get(selectedItem));
        }
    }
    
    public boolean validSelectedItem(int selectedItem) {
        if(selectedItem <= this.getData().size() && selectedItem != -1) {
            return true;
        }
        
        MessageUtil.warning("Nenhum registro selecionado", "Aviso");
        
        return false;
    }
    
    public void processActionAdd(DataObject entity) {
        EntityManager entityMananger = Persistence.createEntityManagerFactory("EpisPU").createEntityManager();
        
        entityMananger.getTransaction().begin();
        
        try {
            entityMananger.persist(entity);
            entityMananger.getTransaction().commit();
            
            MessageUtil.information("Registro incluído", "Aviso");
        } 
        catch (Exception e) {
            entityMananger.getTransaction().rollback();
            
            MessageUtil.error("Erro ao inserir o Registro", "Erro");
        }
        finally {
            entityMananger.close();
            
            this.afterProcessActionAdd();
        }
    }
    
    protected ProcessAction getProcessActionAdd() {
        return (ProcessAction) (DataObject entity) -> this.processActionAdd(entity);
    }
    
    protected void afterProcessActionAdd() {
        this.processUpdateData();
        this.notifyUpdate();
    }
    
    public void processActionEdit(DataObject entity) {
        EntityManager entityMananger = Persistence.createEntityManagerFactory("EpisPU").createEntityManager();
        
        entityMananger.getTransaction().begin();
        
        try {
            entityMananger.merge(entity);
            entityMananger.getTransaction().commit();
            
            MessageUtil.information("Registro atualizado", "Aviso");
        } 
        catch (Exception e) {
            entityMananger.getTransaction().rollback();
            
            System.out.println(e.getMessage());
            
            MessageUtil.error("Erro ao atualizar o Registro", "Erro");
        }
        finally {
            entityMananger.close();
            
            this.afterProcessActionEdit();
        }
    }
    
    protected ProcessAction getProcessActionEdit() {
        return (ProcessAction) (DataObject entity) -> this.processActionEdit(entity);
    }
    
    protected void afterProcessActionEdit() {
        this.processUpdateData();
        this.notifyUpdate();
    }
    
    public void processActionDelete(DataObject entity) {
        EntityManager entityMananger = Persistence.createEntityManagerFactory("EpisPU").createEntityManager();
        
        entityMananger.getTransaction().begin();
        
        try {
            if(!entityMananger.contains(entity)) {
                entity = entityMananger.merge(entity);
            }
            
            entityMananger.remove(entity);
            entityMananger.getTransaction().commit();
            
            MessageUtil.information("Registro excluído", "Aviso");
        } 
        catch (Exception e) {
            entityMananger.getTransaction().rollback();
            
            MessageUtil.error("Erro ao excluir o registro", "Erro");
        }
        finally {
            entityMananger.close();
            
            this.afterProcessActionDelete();
        }
    }
    
    protected ProcessAction getProcessActionDelete() {
        return (ProcessAction) (DataObject entity) -> this.processActionDelete(entity);
    }
    
    protected void afterProcessActionDelete() {
        this.processUpdateData();
        this.notifyUpdate();
    }
    
}
