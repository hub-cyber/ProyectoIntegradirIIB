package com.proyectointegrador2b.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int numElementoPorpagina;
	private int paginaActual;
	private List<PageItem> paginas;
	
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		numElementoPorpagina = page.getSize();
		totalPaginas= page.getTotalPages();
		paginaActual= page.getNumber()+1;
		int desde,hasta;
		if(totalPaginas<= numElementoPorpagina) {
			desde=1;
			hasta= totalPaginas;
		}else {
			if(paginaActual<= numElementoPorpagina/2) {
				desde=1;
				hasta= numElementoPorpagina;
			}else if(paginaActual >= totalPaginas - numElementoPorpagina/2) {
				desde= totalPaginas - numElementoPorpagina +1;
				hasta = numElementoPorpagina;
			}else {
				desde= paginaActual -numElementoPorpagina/2;
				hasta = numElementoPorpagina;
			}
		}
		
		for(int i=0; i< hasta;i++) {
			paginas.add(new PageItem(desde +i , paginaActual== desde+i));
			
		}
	}


	public String getUrl() {
		return url;
	}


	public int getTotalPaginas() {
		return totalPaginas;
	}


	public int getPaginaActual() {
		return paginaActual;
	}


	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean ishasNext() {
		return page.hasNext();
	}
	public boolean ishasPrevious() {
		return page.hasPrevious();
	}
}
