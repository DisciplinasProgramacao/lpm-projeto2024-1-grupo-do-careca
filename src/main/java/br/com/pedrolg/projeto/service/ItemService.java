package br.com.pedrolg.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedrolg.projeto.entity.Item;
import br.com.pedrolg.projeto.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    public Optional<Item> encontrarItemPorId(int id) {
        return itemRepository.findById(id);
    }

    public Item salvarItem(Item item) {
        return itemRepository.save(item);
    }

    public void deletarItem(int id) {
        itemRepository.deleteById(id);
    }
}
