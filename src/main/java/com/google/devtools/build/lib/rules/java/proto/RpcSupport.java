// Copyright 2016 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.rules.java.proto;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.devtools.build.lib.actions.Artifact;
import com.google.devtools.build.lib.analysis.FilesToRunProvider;
import com.google.devtools.build.lib.analysis.RuleContext;
import com.google.devtools.build.lib.analysis.TransitiveInfoCollection;
import com.google.devtools.build.lib.analysis.starlark.Args;
import com.google.devtools.build.lib.packages.AspectDefinition;
import com.google.devtools.build.lib.packages.AspectParameters;
import com.google.devtools.build.lib.packages.StarlarkInfo;
import net.starlark.java.eval.EvalException;

/** Used by java_proto_library to support Google-specific features. */
public interface RpcSupport {
  void populateAdditionalArgs(RuleContext ruleContext, Artifact sourceJar, Args args)
      throws EvalException;

  ImmutableList<FilesToRunProvider> getAdditionalTools(RuleContext ruleContex);

  boolean allowServices(RuleContext ruleContext);

  Optional<StarlarkInfo> getToolchain(RuleContext ruleContext);

  ImmutableList<TransitiveInfoCollection> getRuntimes(RuleContext ruleContext);

  void mutateAspectDefinition(AspectDefinition.Builder def, AspectParameters aspectParameters);

  /** Returns false if the attributes of the rule are invalid */
  boolean checkAttributes(RuleContext ruleContext, AspectParameters aspectParameters);
}
