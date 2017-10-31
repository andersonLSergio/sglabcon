package br.com.sglabcon.bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.sglabcon.dao.TipoRupturaDAO;
import br.com.sglabcon.domain.TipoRuptura;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean
public class TipoRupturaBean extends GenericBean {

	private TipoRuptura tipoRuptura;
	private List<TipoRuptura> tiposRuptura;

	@PostConstruct
	public void listar() {
		try {
			TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
			tiposRuptura = tipoRupturaDAO.listar("tipoRuptura");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar o banco");
			erro.printStackTrace();
		}
	}

	public void novo() {
		tipoRuptura = new TipoRuptura();
	}

	public void salvar() {
		try {
			//se o caminho de origem do arquivo de upload foi preenchido
			//Pode continuar o cadastro
			//do contrário, disparar aviso que o upload não foi feito
			if (tipoRuptura.getCaminhoOrigem() != null) {
				
				TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
				
				//pega o caminho de origem do arquivo temporário na pasta /tmp
				Path origem = Paths.get(tipoRuptura.getCaminhoOrigem());
				
				//se o código ainda não foi preenchido, se trata de um novo cadastro
				if(tipoRuptura.getCodigo() == null) {
					
					tipoRuptura.setImgUrl("n/a");
					Long codRetorno = tipoRupturaDAO.salvarComRetorno(tipoRuptura);
					
					Path destino = Paths
							.get("/home/anderson/eclipse-workspace/Sglabcon/src/main/webapp/resources/tiposRupturaImg/"
									+ codRetorno + ".png");
					
					Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
					
					tipoRuptura = tipoRupturaDAO.buscar(codRetorno);
					
					tipoRuptura.setImgUrl(codRetorno + ".png");
					
				} else {
					
					Path destino = Paths
							.get("/home/anderson/eclipse-workspace/Sglabcon/src/main/webapp/resources/tiposRupturaImg/"
									+ tipoRuptura.getCodigo() + ".png");
					
					tipoRuptura.setImgUrl(tipoRuptura.getCodigo() +".png");

					Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
				}
				

				tipoRupturaDAO.merge(tipoRuptura);
				Messages.addGlobalInfo("Tipo de Ruptura cadastrado com sucesso");

				novo();
				listar();
			} else {
				Messages.addFlashGlobalError("Realize o upload da imagem antes de cadastrar");
			}

		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Houve um erro ao salvar registro");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			tipoRuptura = (TipoRuptura) evento.getComponent().getAttributes().get("tipoRSelecionada");
			TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
			tipoRupturaDAO.excluir(tipoRuptura);

			Path arquivo = Paths
					.get("/home/anderson/eclipse-workspace/Sglabcon/src/main/webapp/resources/tiposRupturaImg/"
							+ tipoRuptura.getCodigo() + ".png");

			Files.deleteIfExists(arquivo);

			Messages.addGlobalInfo("Tipo de Ruptura excluído com sucesso");

			listar();
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir registro");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		tipoRuptura = (TipoRuptura) evento.getComponent().getAttributes().get("tipoRSelecionada");
	}

	public void upload(FileUploadEvent evento) {
		UploadedFile arquivoUpload = evento.getFile();
		try {
			Path arquivoTemp = Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);

			tipoRuptura.setCaminhoOrigem(arquivoTemp.toString());

			Messages.addGlobalInfo("Upload realizado com sucesso");

		} catch (IOException e) {
			Messages.addGlobalError("Ocorreu um erro ao realizar upload de arquivo");
			e.printStackTrace();
		}
	}

	// setters e getters

	public TipoRuptura getTipoRuptura() {
		return tipoRuptura;
	}

	public void setTipoRuptura(TipoRuptura tipoRuptura) {
		this.tipoRuptura = tipoRuptura;
	}

	public List<TipoRuptura> getTiposRuptura() {
		return tiposRuptura;
	}

	public void setTiposRuptura(List<TipoRuptura> tiposRuptura) {
		this.tiposRuptura = tiposRuptura;
	}

}
