
#pragma once

#include <fs_types.hxx>
#include <problem.hxx>
#include "external.hxx"

#include <component_factory.hxx>

using namespace fs0;

extern std::unique_ptr<External> external;



class ComponentFactory : public BaseComponentFactory {
    std::map<std::string, Function> instantiateFunctions() const {
		return {
			
		};
	}
};

/* Generate the whole planning problem */
void generate(const rapidjson::Document& data, const std::string& data_dir);
