package test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import org.example.dao.ClienteDAO;
import org.example.dao.IClienteDAO;
import org.example.domain.Cliente;
import org.example.exceptions.DAOException;
import org.example.exceptions.MaisDeUmRegistroException;
import org.example.exceptions.TableException;
import org.example.exceptions.TipoChaveNaoEncontradaException;


public class ClienteDAOTest {

    private IClienteDAO clienteDao;

    public ClienteDAOTest() {
        clienteDao = new ClienteDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<Cliente> list = clienteDao.buscarTodos();
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCpf());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(12312312312L);
        cliente.setNome("Pedro");
        cliente.setCidade("Goias");
        cliente.setEnd("End");
        cliente.setEstado("GO");
        cliente.setEmail("pedro@gmail.com");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        clienteDao.cadastrar(cliente);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCpf());
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("Pedro");
        cliente.setCidade("Goias");
        cliente.setEnd("End");
        cliente.setEstado("GO");
        cliente.setEmail("pedro@gmail.com");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCpf());
    }


    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("Pedro");
        cliente.setCidade("Goias");
        cliente.setEnd("End");
        cliente.setEstado("GO");
        cliente.setEmail("pedro@gmail.com");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCpf());
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("Pedro");
        cliente.setCidade("Goias");
        cliente.setEnd("End");
        cliente.setEstado("GO");
        cliente.setEmail("pedro@gmail.com");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteConsultado.setNome("Pedro");
        clienteDao.alterar(clienteConsultado);

        Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Pedro", clienteAlterado.getNome());

        clienteDao.excluir(cliente.getCpf());
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("Pedro");
        cliente.setCidade("Goias");
        cliente.setEnd("End");
        cliente.setEstado("GO");
        cliente.setEmail("pedro@gmail.com");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente cliente1 = new Cliente();
        cliente1.setCpf(56565656569L);
        cliente1.setNome("Pedro");
        cliente1.setCidade("Goias");
        cliente1.setEnd("End");
        cliente1.setEstado("GO");
        cliente1.setEmail("pedro@gmail.com");
        cliente1.setNumero(10);
        cliente1.setTel(1199999999L);
        Boolean retorno1 = clienteDao.cadastrar(cliente1);
        Assert.assertTrue(retorno1);

        Collection<Cliente> list = clienteDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCpf());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<Cliente> list1 = clienteDao.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }
}