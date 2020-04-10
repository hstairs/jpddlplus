
#pragma once

#include <cassert>
#include <cstring>
#include <stdexcept>
#include <unordered_set>
#include <unordered_map>

#include <fs_types.hxx>
#include <utils/serializer.hxx>

using namespace fs0;

/*********************************************/
/* The static data                           */
/*********************************************/
class ExternalBase  {
protected:
    

public:
    ExternalBase(const std::string& data_dir) 
    {}

    

    //! By default, we register no component
    virtual void registerComponents() const {}
};
