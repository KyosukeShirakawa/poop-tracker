package com.poop_tracker.mapper;

import com.poop_tracker.dto.PoopDTO;
import com.poop_tracker.entity.Poop;

public class PoopMapper {
    public static PoopDTO mapToPoopDto(Poop poop) {
        PoopDTO poopDTO = new PoopDTO();
        poopDTO.setColor(poop.getColor());
        poopDTO.setSize(poop.getSize());
        poopDTO.setSoftness(poop.getSoftness());

        return  poopDTO;
    }
    public static Poop mapToPoop(PoopDTO poopDTO) {
        Poop poop = new Poop();
        poop.setColor(poopDTO.getColor());
        poop.setSize(poopDTO.getSize());
        poop.setSoftness(poopDTO.getSoftness());

        return poop;
    }
}
