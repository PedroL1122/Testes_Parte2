package org.example.services;

import org.example.dao.IProdutoDAO;
import org.example.domain.Produto;
import org.example.services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

    public ProdutoService(IProdutoDAO dao) {
        super(dao);
    }
}