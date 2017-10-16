package br.com.sglabcon.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.ClasseMaquinaDAO;
import br.com.sglabcon.domain.ClasseMaquina;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ClasseMaquinaBean{
	
	private ClasseMaquina classeMaquina;
	private List<ClasseMaquina> classes;
	
	@PostConstruct
	public void listar() {
		try {
			ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
			classes = classeMaquinaDAO.listar("classe");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao fazer consulta no banco");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		classeMaquina = new ClasseMaquina();
	}
	
	public void salvar() {
		try {
			ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
			classeMaquinaDAO.merge(classeMaquina);
			Messages.addGlobalInfo("Classe de Máquina salva com sucesso");
			novo();
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao salvar registro");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			classeMaquina = (ClasseMaquina) evento.getComponent().getAttributes().get("classeSelecionada");
			
			ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
			classeMaquinaDAO.excluir(classeMaquina);
			Messages.addGlobalInfo("Classe de Máquina excluída com sucesso");
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao excluir registro");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		classeMaquina = (ClasseMaquina) evento.getComponent().getAttributes().get("classeSelecionada");
	}
	
	//setters e getters
	
	public ClasseMaquina getClasseMaquina() {
		return classeMaquina;
	}
	
	public void setClasseMaquina(ClasseMaquina classeMaquina) {
		this.classeMaquina = classeMaquina;
	}
	
	public List<ClasseMaquina> getClasses() {
		return classes;
	}
	
	public void setClasses(List<ClasseMaquina> classes) {
		this.classes = classes;
	}
}
