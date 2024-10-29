// Paso 2: Consulta para obtener los resultados paginados
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.sort(Sort.Direction.DESC, "fecha_modificacion"),
                Aggregation.project()
                        .and(ControlBusinessConstants.COE).as("coe")
                        .and(ControlBusinessConstants.AREA_BUSINESS).as("area")
                        .and(ControlBusinessConstants.TYPE_FORO).as("typeForo")
                        .and(ControlBusinessConstants.STATUS).as("status")
                        .and(ControlBusinessConstants.DATE_INI).as("dateToString('%Y-%m-%d', " +
                                                                   "dateFromString(dateIni))")
                        .and(ControlBusinessConstants.DATE_FIN).as("dateToString('%Y-%m-%d', " +
                                                                   "dateFromString(dateFin))")
                        .and("_id").as("number"),
                Aggregation.skip(pageable.getOffset()),
                Aggregation.limit(pageable.getPageSize())
        );
