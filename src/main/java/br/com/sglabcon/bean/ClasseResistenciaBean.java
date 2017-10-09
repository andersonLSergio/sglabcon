package br.com.sglabcon.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.ClasseResistenciaDAO;
import br.com.sglabcon.domain.ClasseResistencia;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@ViewScoped
public class ClasseResistenciaBean implements Serializable {

	private ClasseResistencia classeResistencia;
	private List<ClasseResistencia> classesResistencia;

	public ClasseResistencia getClasseResistencia() {
		return classeResistencia;
	}

	public void setClasseResistencia(ClasseResistencia classeResistencia) {
		this.classeResistencia = classeResistencia;
	}

	public List<ClasseResistencia> getClassesResistencia() {
		return classesResistencia;
	}

	public void setClassesResistencia(List<ClasseResistencia> classesResistencia) {
		this.classesResistencia = classesResistencia;
	}

	@PostConstruct
	public void listar() {
		try {
			ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
			classesResistencia = classeResistenciaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao consultar o banco.");
			erro.printStackTrace();
		}

	}

	public void novo() {
		classeResistencia = new ClasseResistencia();
	}

	public void salvar() {
		try {
			ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
			classeResistenciaDAO.merge(classeResistencia);
			
			Messages.addFlashGlobalInfo("Classe de Resistência salva com sucesso!");
			
			novo();
			listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar alterações.");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			classeResistencia = (ClasseResistencia) evento.getComponent().getAttributes().get("crSelecionada");
			
			ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
			classeResistenciaDAO.excluir(classeResistencia);
			
			Messages.addFlashGlobalInfo("Classe de Resistência excluída com sucesso!");
			
			listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir Classe de Resistência.");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			classeResistencia = (ClasseResistencia) evento.getComponent().getAttributes().get("crSelecionada");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao selecionar Classe de Resistẽncia.");
			erro.printStackTrace();
		}
	}

}
